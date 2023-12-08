package org.key;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.key.domain.Customer;
import org.key.domain.LongKey;
import org.key.repository.CustomerRepository;
import org.key.repository.LongKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/*Average Load times - Id
Average Write Time: 1.0291 ms
Average Read Time: 0.7325 ms
*/

@SpringBootTest
@SpringJUnitConfig
public class LongKeyPerformanceTest {

	@Autowired
	private LongKeyRepository longKeyRepository;

	@Autowired
	private CustomerRepository customerRepository;

	private Long loadValue = 1L;

	@Test
	public void testLongKeyPerformance() throws InterruptedException {
		List<Long> idList = new ArrayList<>();
		long totalWriteTime = 0;
		long totalReadTime = 0;
		long totalWriteTimeChild = 0;
		long totalReadTimeChild = 0;

		long startTime = 0;
		long endTime = 0;

		// Write
		for (long i = 0; i < loadValue; i++) {
			final long index = i;
			LongKey longKey = new LongKey();
			Customer customer = new Customer();
			longKey.setFirstName("First Name " + index);
			longKey.setLastName("Last Name " + index);
			customer.setId(i);
			customer.setLongKeyRef(longKey);

			// Save Parent Activity
			startTime = System.currentTimeMillis();
			longKeyRepository.save(longKey);
			endTime = System.currentTimeMillis();

			// Save Child Activity
			startTime = System.currentTimeMillis();
			try {
				customerRepository.save(customer);
			} catch (Exception e) {
				e.printStackTrace();
			}
			endTime = System.currentTimeMillis();

			idList.add(longKey.getId());
			totalWriteTime += (endTime - startTime);
			totalWriteTimeChild += (endTime - startTime);
		}

		// Read Time
		for (Long idValue : idList) {
			startTime = System.currentTimeMillis();
			longKeyRepository.findById(idValue).orElse(null);
			endTime = System.currentTimeMillis();
			totalReadTime += (endTime - startTime);

			startTime = System.currentTimeMillis();
			customerRepository.getCustomers(idValue);
			endTime = System.currentTimeMillis();
			totalReadTimeChild += (endTime - startTime);
		}

		double averageWriteTime = (double) totalWriteTime / loadValue;
		System.out.println("Average Write Time: " + averageWriteTime + " ms");
		assertThat("Average Write Time: " + averageWriteTime + " ms");

		double averageReadTime = (double) totalReadTime / loadValue;
		System.out.println("Average Read Time: " + averageReadTime + " ms");
		assertThat("Average Read Time: " + averageReadTime + " ms");
		
		double averageWriteTimeChild = (double) totalWriteTimeChild / loadValue;
		System.out.println("Average Write Time Child: " + averageWriteTimeChild + " ms");
		assertThat("Average Write Time: " + averageWriteTimeChild + " ms");

		double averageReadTimeChild = (double) totalReadTimeChild / loadValue;
		System.out.println("Average Read Time Child: " + averageReadTimeChild + " ms");
		assertThat("Average Read Time: " + averageReadTimeChild + " ms");

	}

}