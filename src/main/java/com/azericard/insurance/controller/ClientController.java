package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Client;
import com.azericard.insurance.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Client>> get_all(@RequestHeader("authToken") String role) {
        List<Client> all = service.getAll(role);
        if (all.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(all, HttpStatus.FOUND);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        Client client = service.getOne(id, role);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.FOUND);
    }

    @PutMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> create(@RequestBody Client client, @RequestHeader("authToken") String role) {
        Client cl= service.save(client, role);
        if (cl == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(client, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Client client, @RequestHeader("authToken") String role) {
        service.delete(client, role);
    }
}
