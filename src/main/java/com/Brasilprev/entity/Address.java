package com.Brasilprev.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "T_ADDRESS")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

}
