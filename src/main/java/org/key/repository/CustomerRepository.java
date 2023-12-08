package org.key.repository;

import org.key.domain.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	@Query(value="select c from Customer c where c.longKeyRef.id = :id")
	public Customer getCustomers(@Param("id") Long id);

}
