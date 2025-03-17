package com.example.market.service;

import com.example.market.entity.Client;
import com.example.market.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client clientSave(Client client){
        return clientRepository.save(client);
    }

    public List<Client> clientList(){
        return clientRepository.findAll();
    }

    public Optional<Client> clientById(Long id){
        return clientRepository.findById(id);
    }

    public void clientDeleteById (Long id){
        clientRepository.deleteById(id);
    }
}
