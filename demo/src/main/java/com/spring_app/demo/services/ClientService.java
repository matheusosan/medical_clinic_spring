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

        Client client = new Client();
        client.setEmail(dto.getEmail());
        client.setName(dto.getName());
        client.setCpf(dto.getCpf());
        client.setBirthDate(dto.getBirthDate());
        client.setPhoneNumber(dto.getPhoneNumber());

        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    };

    public Client findClientByCPF(String cpf) {
        return clientRepository.findByCpf(cpf).orElseThrow(() -> new ClientNotFoundException("Usuário não encontrado com o CPF fornecido!"));
    };

    public Client findById(long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado."));
    }

}
