package com.Brasilprev.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Brasilprev.entity.Client;

import com.Brasilprev.service.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/client")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ClientController {
	private ClientService clientService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public String create(@RequestBody Client client) {
		clientService.addClient(client);
		return "Criado";
	}

	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<Client> listClient() {
		return clientService.getAllClients();
	}

	@GetMapping("/{cpf}")
	@ResponseStatus(HttpStatus.OK)
	public String clientInfo(@PathVariable String cpf) {
		return clientService.getClient(cpf);

	}

	@GetMapping("/address")
	@ResponseStatus(HttpStatus.OK)
	public String updateClient(@RequestBody Client client) {
		clientService.updateClient(client);
		return clientInfo(client.getCpf());
	}

	@DeleteMapping("/{cpf}")
	public ResponseEntity<Client> deleteClient(@PathVariable String cpf) {
		clientService.deleteClient(cpf);
		return ResponseEntity.ok().build();
	}

}
