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

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ClientServiceTest {

    @InjectMocks
    private ClientService clientService;

    @Mock
    private ClientRepository clientRepository;

    @Test
    //Positive test for client creation
    public void whenValidClientGivenThenCreateClient() throws ClientAlreadyRegisteredException {
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findById(client.getCpf())).thenReturn(Optional.empty());
        when(clientRepository.save(client)).thenReturn(client);

        Client createdClient = clientService.addClient(client);

        assertEquals(createdClient, client);
    }

    @Test
    //Negative test for client creation (client already registered)
    public void whenClientAlreadyExistsGivenThenCreateClient() {
        Client client = ClientBuilder.builder().build().client();
        when(clientRepository.findById(client.getCpf())).thenReturn(Optional.of(client));
        Assertions.assertThrows(ClientAlreadyRegisteredException.class,
                () -> clientService.addClient(client));
    }

    @Test
    //Positive test for client listing
    public void whenListClientIsCalledThenReturnAListOfClients() {
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findAll()).thenReturn(Collections.singletonList(client));

        List<Client> foundClient = clientService.getAllClients();

        assertEquals(foundClient.get(0), client);
    }

    @Test
    //Negative test for customer listing (empty list)
    public void whenListClientIsCalledThenReturnAnEmptyOfClients() {
        when(clientRepository.findAll()).thenReturn(Collections.EMPTY_LIST);

        List<Client> foundClient = clientService.getAllClients();

        assertEquals(new ArrayList<>(0), foundClient);
    }

    @Test
    //Positive test for client update
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
    //Negative test for client update (non-registered customer)
    public void whenInValidClientGivenThenReturnUpdateClient() throws ClientNotFoundException{
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findByCpf(client.getCpf())).thenReturn(null);

        Assertions.assertThrows(ClientNotFoundException.class,
                () -> clientService.updateClient(client, client.getCpf()));
    }

    @Test
    //Positive test for client deletion
    public void whenExclusionValidIdThenAClientShouldBeDeleted(){
        Client client = ClientBuilder.builder().build().client();

        when(clientRepository.findByCpf(client.getCpf())).thenReturn(client);

        clientService.deleteClient(client.getCpf());
        verify(clientRepository).delete(client);
    }

    @Test
    //Negative test for customer exclusion (non-registered customer)
    public void whenExclusionInValidIdThenAClientShouldBeDeleted(){
        Client client = ClientBuilder.builder().build().client();

        assertThatExceptionOfType(ClientNotFoundException.class)
                .isThrownBy(() -> clientService.deleteClient(client.getCpf()))
                .withMessage("Client not found");
    }

    @Test
    //Positive test for listing a client information according to their cpf
    public void whenValidCpfClientIsGivenThenReturnAClient() {
        Client expectedFoundClient = ClientBuilder.builder().build().client();

        when(clientRepository.findByCpf(expectedFoundClient.getCpf())).thenReturn(expectedFoundClient);

        Client foundClient = clientService.getClient(expectedFoundClient.getCpf());

        assertEquals(foundClient, expectedFoundClient);
    }

    @Test
    //Negative test for listing a client information according to their cpf (there is no client registered with the informed cpf)
    public void whenInValidCpfClientIsGivenThenReturnAClient() throws ClientNotFoundException {
        Client expectedFoundClient = ClientBuilder.builder().build().client();
        when(clientRepository.findByCpf(expectedFoundClient.getCpf()))
                .thenReturn(null);


        Assertions.assertThrows(ClientNotFoundException.class,
                () -> clientService.getClient(expectedFoundClient.getCpf()));

    }
}

