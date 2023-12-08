package org.key;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.key.domain.UUIDKey;
import org.key.repository.UUIDRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;


/*Average Load times - UUID
		Average Write Time: 0.9215 ms
		Average Read Time: 0.5754 ms
*/

/*Average Load times - TSID
Average Write Time: 0.9199 ms
Average Read Time: 0.5908 ms
*/


/*Average Load times - Id
Average Write Time: 0.8161 ms
Average Read Time: 0.6358 ms
*/

@SpringBootTest
@SpringJUnitConfig
public class UUIDKeyPerformanceTest {

	@Autowired
	private UUIDRepository uuidKeyRepository;

	private Long loadValue = 10000L;

	@Test
	public void testLongKeyPerformance() throws InterruptedException {
		List<UUID> uuidList = new ArrayList<>();
		// Record Write and Read Times
		long totalWriteTime = 0;
		long totalReadTime = 0;

		long startTime = 0;
		long endTime = 0;

		for (long i = 0; i < loadValue; i++) {
			final long index = i;
			UUIDKey uuidKey = new UUIDKey();
			uuidKey.setFirstName("First Name " + index);
			uuidKey.setLastName("Last Name " + index);
			startTime = System.currentTimeMillis();
			uuidKeyRepository.save(uuidKey);
			endTime = System.currentTimeMillis();
			uuidList.add(uuidKey.getId());
			totalWriteTime += (endTime - startTime);
		}

		// Read Time
		for (UUID uuid : uuidList) {
			startTime = System.currentTimeMillis();
			uuidKeyRepository.findById(uuid).orElse(null);
			endTime = System.currentTimeMillis();
			totalReadTime += (endTime - startTime);
		}

		double averageWriteTime = (double) totalWriteTime / loadValue;
		System.out.println("Average Write Time: " + averageWriteTime + " ms");
		assertThat("Average Write Time: " + averageWriteTime + " ms");

		double averageReadTime = (double) totalReadTime / loadValue;
		System.out.println("Average Read Time: " + averageReadTime + " ms");
		assertThat("Average Read Time: " + averageReadTime + " ms");

	}

}