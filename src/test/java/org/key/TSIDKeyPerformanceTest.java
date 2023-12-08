package org.key;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.key.domain.TSIDKey;
import org.key.repository.TSIDKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import io.hypersistence.tsid.TSID;

/*Average Load times - TSID
Average Write Time: 0.9808 ms
Average Read Time: 0.5955 ms
*/

@SpringBootTest
@SpringJUnitConfig
public class TSIDKeyPerformanceTest {

	@Autowired
	private TSIDKeyRepository tsidKeyRepository;

	private Long loadValue = 10000L;

	@Test
	public void testLongKeyPerformance() throws InterruptedException {

		List<Long> tsList = new ArrayList<>();

		// Record Write and Read Times
		long totalWriteTime = 0;
		long totalReadTime = 0;

		long startTime = 0;
		long endTime = 0;

		for (long i = 0; i < loadValue; i++) {
			final long index = i;
			TSIDKey tsidKey = new TSIDKey();
			tsidKey.setId(TSID.fast().toLong());
			tsidKey.setFirstName("First Name " + index);
			tsidKey.setLastName("Last Name " + index);
			startTime = System.currentTimeMillis();
			tsidKeyRepository.save(tsidKey);
			endTime = System.currentTimeMillis();
			tsList.add(tsidKey.getId());
			totalWriteTime += (endTime - startTime);
		}

		// Read Time
		for (Long tsid : tsList) {
			startTime = System.currentTimeMillis();
			tsidKeyRepository.findById(tsid).orElse(null);
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