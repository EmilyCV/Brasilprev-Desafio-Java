package com.Brasilprev.service;

import com.Brasilprev.builder.ClientBuilder;
import com.Brasilprev.entity.Client;
import com.Brasilprev.exception.ClientAlreadyRegisteredException;
import com.Brasilprev.exception.ClientNotFoundException;
import com.Brasilprev.repository.ClientRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    public void whenValidClientGivenThenCreateClient() throws ClientAlreadyRegisteredException {
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findById(client.getCpf())).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.addClient(client);

        assertEquals(createdClient, client);
    }

    @Test
    public void whenClientAlreadyExistsGivenThenCreateClient() {
        Client client = ClientBuilder.builder().build().client();
        when(clientRepository.findById(client.getCpf())).thenReturn(Optional.of(client));


        assertThrows(ClientAlreadyRegisteredException.class, () -> clientService.addClient(client));
    }

    @Test
    public void whenListClientIsCalledThenReturnAListOfClients() {
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));

        List<Client> foundClient = clientService.getAllClients();

        assertEquals(foundClient.get(0), client);
    }

    @Test
    public void whenListClientIsCalledThenReturnAnEmptyOfClients() {
        when(clientRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<Client> foundClient = clientService.getAllClients();

        assertEquals(new ArrayList<>(0), foundClient);
    }

    @Test
    public void whenValidClientIsGivenThenReturnUpdatedClient() {
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findByCpf(client.getCpf())).thenReturn(client);

        String email = "marceloMil@gmail.com";

        client.setEmail(email);

        when(clientRepository.save(client)).thenReturn(client);

        Client newClient = clientService.updateClient(client, client.getCpf());

        assertEquals(email, newClient.getEmail());
    }

    @Test
    public void whenInValidClientGivenThenReturnUpdateClient() throws ClientNotFoundException{
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findByCpf(client.getCpf())).thenReturn(null);

        Client clientFound = clientService.updateClient(client, client.getCpf());
        assertNotEquals(clientFound, client);
    }

 

    @Test
    public void whenValidCpfClientIsGivenThenReturnAClient() {
        // given
        Client expectedFoundClient = ClientBuilder.builder().build().client();

        // when
        when(clientRepository.findByCpf(expectedFoundClient.getCpf())).thenReturn(expectedFoundClient);

        // then
        Client foundClient = clientService.getClient(expectedFoundClient.getCpf());

        assertEquals(foundClient, expectedFoundClient);
    }

    @Test
    public void whenInValidCpfClientIsGivenThenReturnAClient() throws ClientNotFoundException {
        Client expectedFoundClient = ClientBuilder.builder().build().client();
        when(clientRepository.findByCpf(expectedFoundClient.getCpf()))
                .thenReturn(expectedFoundClient);


        Assertions.assertThrows(ClientNotFoundException.class,
                () -> clientService.getClient(expectedFoundClient.getCpf()));

//        String invalidCpf = "12345678965";
//
//        ClientNotFoundException thrown = assertThrows(
//                ClientNotFoundException.class,
//                () -> clientService.getClient(invalidCpf),
//                "Client not found");
//
//        assertTrue(thrown.getMessage().contains("Client not found"));

//        when(clientRepository.findByCpf(anyString())).thenThrow(new ClientNotFoundException());
//
//        Client foundClient = clientService.getClient(invalidCpf);
//
//        assertNull(foundClient);
    }
    //Wrong test
}

