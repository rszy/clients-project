package com.rszy.clientsproject.controller;


import com.rszy.clientsproject.dto.ClientDTO;
import com.rszy.clientsproject.dto.CreateClientDTO;
import com.rszy.clientsproject.exeptions.ClientAlreadyExist;
import com.rszy.clientsproject.exeptions.ClientDataInvalid;
import com.rszy.clientsproject.exeptions.ClientNotFound;
import com.rszy.clientsproject.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    private List<ClientDTO> getAllClients(){
        return clientService.getAllClients();
    }

    @PostMapping
    private ClientDTO createUser(@RequestBody CreateClientDTO createClientDTO) throws ClientAlreadyExist, ClientNotFound, ClientDataInvalid {
        return clientService.addClient(createClientDTO);
    }
}
