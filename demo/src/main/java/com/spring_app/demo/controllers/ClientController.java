package com.spring_app.demo.controllers;

import com.spring_app.demo.dtos.ClientRequestDTO;
import com.spring_app.demo.entities.Client;
import com.spring_app.demo.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping()
    ResponseEntity<Client> createClient(@RequestBody ClientRequestDTO dto) {
        Client newClient = clientService.createClient(dto);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newClient).toUri();

        return ResponseEntity.created(location).body(newClient);
    }

    @GetMapping()
    List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/cpf/{cpf}")
    ResponseEntity<Client> findByCPF(@PathVariable String cpf) {
        Optional<Client> client = clientService.findClientByCPF(cpf);

        return ResponseEntity.ok(client.get());
    }


}
