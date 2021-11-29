package com.Brasilprev.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import com.Brasilprev.entity.Client;

import com.Brasilprev.service.ClientService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/client")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Tag(name = "client")
@CrossOrigin(origins = "*")
public class ClientController {
    private ClientService clientService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
//    @ApiOperation(value = "Client registration")
    public String create(@RequestBody @Valid Client client) {
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
    public Client clientInfo(@PathVariable String cpf) {
        return clientService.getClient(cpf);

    }

    @PutMapping("/{cpf}")
    @ResponseStatus(HttpStatus.OK)
    public Client updateClient(@PathVariable String cpf, @RequestBody @Valid Client client) {
        clientService.updateClient(client, cpf);
        return clientInfo(client.getCpf());
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Client> deleteClient(@PathVariable String cpf) {
        clientService.deleteClient(cpf);
        return ResponseEntity.ok().build();
    }

}
