package org.key.domain;

import org.hibernate.annotations.GenericGenerator;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TSIDKey {

	public TSIDKey() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;
	private String firstName;
	private String lastName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
