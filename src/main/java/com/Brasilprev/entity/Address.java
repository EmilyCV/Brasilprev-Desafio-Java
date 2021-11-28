package com.Brasilprev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity(name = "T_ADDRESS")
@Data
public class Address {
	@Id
	@Column(name = "id_address", unique = true)
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "add")
	private int id;

	@Column(name = "address", nullable = false, length = 300)
	private String address;

	@Column(name = "address_cep", nullable = false, length = 8)
	private String cep;

	@Column(name = "address_state", nullable = false, length = 2)
	private String state;

	@Column(name = "address_number", nullable = false, length = 5)
	private String number;

	@Column(name = "address_complement", length = 60)
	private String complement;
	
//	@ManyToOne
//    @JoinColumn
//	private Client client;
}
