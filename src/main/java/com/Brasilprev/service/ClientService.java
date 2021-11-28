package com.Brasilprev.service;

import org.springframework.stereotype.Service;

import com.Brasilprev.entity.Client;
import com.Brasilprev.exception.ClientAlreedyRegisteredException;
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
	
	public String getClient(String cpf) {
		Client clientExist = clientRepository.findByCpf(cpf);
		if(clientExist != null) {
			return clientExist.toString();
		}
		
		throw new ClientNotFoundException();
	}
	
	public String addClient(Client client) {
		Optional<Client> clientExist = clientRepository.findById(client.getCpf());
		
		if(!clientExist.isEmpty()) {
			throw new ClientAlreedyRegisteredException("Client alreedy registered!");
		}
		
		client.setPassword(AuthenticationService.getPasswordEncoder().encode(client.getPassword()));
		clientRepository.save(client);
		return "Cadastrado";
	}
	
	public String updateClient(Client client) {
		Client clientExist = clientRepository.findByCpf(client.getCpf());
		
		if(clientExist==null) {
			throw new ClientNotFoundException("Client not found");
			
		}
		client.setPassword(AuthenticationService.getPasswordEncoder().encode(client.getPassword()));
		clientRepository.save(client);
		return "Atualizado";
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
