package com.example.market.http.controller;

import com.example.market.entity.Client;
import com.example.market.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Client> clientList(){
        return clientService.clientList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client clientSave(@RequestBody Client client){
        return clientService.clientSave(client);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clientDelete(@PathVariable("id") Long id){
        clientService.clientById(id)
                .map(client -> {
                    clientService.clientDeleteById(client.getId());
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void clientUpdate(@PathVariable("id") Long id, Client client){
        clientService.clientById(id)
                .map(clientBase ->{
                    modelMapper.map(client, clientBase);
                    clientService.clientSave(clientBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Client not found."));
    }

}
