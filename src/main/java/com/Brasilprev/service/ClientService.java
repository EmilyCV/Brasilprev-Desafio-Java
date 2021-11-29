package com.Brasilprev.service;

import org.springframework.stereotype.Service;

import com.Brasilprev.entity.Client;
import com.Brasilprev.exception.ClientAlreadyRegisteredException;
import com.Brasilprev.exception.ClientNotFoundException;
import com.Brasilprev.repository.ClientRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientService {
	
	private ClientRepository clientRepository;
	
	public List<Client> getAllClients(){
		return clientRepository.findAll();
	}
	
	public Client getClient(String cpf) {
		Client clientExist = clientRepository.findByCpf(cpf);
		if(clientExist != null) {
			return clientExist;
		}
		
		throw new ClientNotFoundException();
	}
	
	public Client addClient(Client client) {
		Optional<Client> clientExist = clientRepository.findById(client.getCpf());
		
		if(!clientExist.isEmpty()) {
			throw new ClientAlreadyRegisteredException("Client already registered!");
		}
		
		client.setPassword(AuthenticationService.getPasswordEncoder().encode(client.getPassword()));

		return clientRepository.save(client);
	}
	
	public Client updateClient(Client client, String cpf) {
		Client clientExist = clientRepository.findByCpf(cpf);
		
		if(clientExist==null) {
			throw new ClientNotFoundException("Client not found");
			
		}
		client.setPassword(AuthenticationService.getPasswordEncoder().encode(client.getPassword()));

		return clientRepository.save(client);
	}
	
	public String deleteClient(String cpf) {
		Client client = clientRepository.findByCpf(cpf);
		if(client == null) {
			throw new ClientNotFoundException("Client not found");
		}
		clientRepository.delete(client);
		return "Deletado";
	}
	

}
