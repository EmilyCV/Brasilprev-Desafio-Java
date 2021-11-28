	package com.Brasilprev.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.Brasilprev.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String>{
	Client findByCpf(String cpf);
	Optional<Client> findByEmail(String email);
}
