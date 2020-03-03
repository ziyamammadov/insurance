package com.azericard.insurance.controller;

import com.azericard.insurance.entity.Client;
import com.azericard.insurance.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @GetMapping("/all")
    public List<Client> get_all(@RequestHeader("authToken") String role) {
        return service.getAll(role);
    }

    @GetMapping("/{id}")
    public Client get_one(@PathVariable long id, @RequestHeader("authToken") String role) {
        return service.getOne(id, role);
    }

    @PutMapping("/save")
    public Client create(@RequestBody Client client, @RequestHeader("authToken") String role) {
        return service.save(client, role);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody Client client, @RequestHeader("authToken") String role) {
        service.delete(client, role);
    }
}
