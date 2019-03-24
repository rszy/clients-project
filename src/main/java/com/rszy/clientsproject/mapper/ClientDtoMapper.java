package com.rszy.clientsproject.mapper;

import com.rszy.clientsproject.dto.ClientDTO;
import com.rszy.clientsproject.dto.CreateClientDTO;
import com.rszy.clientsproject.model.Client;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClientDtoMapper {

    public ClientDTO mapModelToDto (Client client) {
        return new ClientDTO(client.getClientId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPesel(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getAddressList());
    }

    public Client mapDtoToModel (CreateClientDTO createClientDTO) {
        return new Client(createClientDTO.getFirstName(),
                createClientDTO.getLastName(),
                createClientDTO.getPesel(),
                createClientDTO.getEmail(),
                createClientDTO.getPhoneNumber(),
                new ArrayList<>());
    }
}
