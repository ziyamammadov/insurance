package com.azericard.insurance.service;

import com.azericard.insurance.data.ClientRepository;
import com.azericard.insurance.entity.Client;
import com.azericard.insurance.entity.EncodedRole;
import com.azericard.insurance.exception.AccessNotAllowedException;
import com.azericard.insurance.exception.ClientNotFoundException;
import com.azericard.insurance.exception.GeneralException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            List<Client> clients = (List<Client>) clientRepository.findAll();
            if (clients.isEmpty()) {
                throw new GeneralException("No Data Found");
            }
            return clients;
        }
        throw new AccessNotAllowedException();
    }

    public Client getOne(long id, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            Optional<Client> optionalClient = clientRepository.findById(id);
            return optionalClient.orElseThrow(ClientNotFoundException::new);
        }
        throw new AccessNotAllowedException();

    }

    public Client save(Client client, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            return clientRepository.save(client);
        }
        throw new AccessNotAllowedException();

    }

    public void delete(Client client, String role) {
        if (role.equals(EncodedRole.ADMIN)) {
            clientRepository.delete(client);
        }
        throw new AccessNotAllowedException();
    }
}
