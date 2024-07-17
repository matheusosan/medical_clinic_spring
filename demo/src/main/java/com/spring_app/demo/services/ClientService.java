package com.spring_app.demo.services;

import com.spring_app.demo.dtos.ClientRequestDTO;
import com.spring_app.demo.entities.Client;
import com.spring_app.demo.exceptions.ClientExceptions.ClientAlreadyExistsException;
import com.spring_app.demo.exceptions.ClientExceptions.ClientNotFoundException;
import com.spring_app.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client createClient(ClientRequestDTO dto) {

        Optional<Client> clientExists = clientRepository.findByCpf(dto.getCpf());

        if(clientExists.isPresent()) {
            throw new ClientAlreadyExistsException("Usuário já cadastrado no sistema!");
        }

        Client client = new Client.Builder()
                .withCpf(dto.getCpf())
                .withEmail(dto.getEmail())
                .withName(dto.getName())
                .withPhoneNumber(dto.getPhoneNumber()).build();

        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    };

    public Optional<Client> findClientByCPF(String cpf) {
        Optional<Client> client = clientRepository.findByCpf(cpf);

        if(client.isEmpty()) {
            throw new ClientNotFoundException( "Usuário não encontrado com o CPF fornecido!");
        }

        return client;
    };


    ResponseEntity<Void> updateClient() {
        return null;
    }

}
