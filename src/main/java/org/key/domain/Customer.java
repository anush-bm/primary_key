package org.key.domain;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "longkey_id", referencedColumnName = "id")
	private LongKey longKeyRef;

	/*
	 * @ManyToOne private TSIDKey tsKeyRef;
	 * 
	 * @ManyToOne private UUIDKey uuidKeyRef;
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LongKey getLongKeyRef() {
		return longKeyRef;
	}

	public void setLongKeyRef(LongKey longKeyRef) {
		this.longKeyRef = longKeyRef;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", longKeyRef=" + longKeyRef + "]";
	}

}
