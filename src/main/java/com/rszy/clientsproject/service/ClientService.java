package com.rszy.clientsproject.service;

import com.rszy.clientsproject.dto.ClientDTO;
import com.rszy.clientsproject.dto.CreateClientDTO;
import com.rszy.clientsproject.exeptions.ClientAlreadyExist;
import com.rszy.clientsproject.exeptions.ClientDataInvalid;
import com.rszy.clientsproject.exeptions.ClientNotFound;
import com.rszy.clientsproject.mapper.ClientDtoMapper;
import com.rszy.clientsproject.model.Client;
import com.rszy.clientsproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientDtoMapper clientDtoMapper;

    @Autowired
    private ClientRepository clientRepository;

    public List<ClientDTO> getAllClients(){
        List<ClientDTO> clientDTOList = new ArrayList<>();
        for (Client client : clientRepository.findAll()) {
            clientDTOList.add(clientDtoMapper.mapModelToDto(client));
        }
        return clientDTOList;
    }

    private ClientDTO getClientByPesel(String pesel) throws ClientNotFound {
        for (Client client : clientRepository.findAll()) {
            if(client.getPesel() == pesel){
                return clientDtoMapper.mapModelToDto(client);
            }
        }
        throw new ClientNotFound();
    }

    public ClientDTO addClient (CreateClientDTO createClientDTO) throws ClientAlreadyExist, ClientDataInvalid {
        if(createClientDTO.getFirstName() == null || createClientDTO.getLastName() == null || createClientDTO.getPesel() == null) {
            throw new ClientDataInvalid();
        }

        try {
            getClientByPesel(createClientDTO.getPesel());
            throw  new ClientAlreadyExist();
        } catch (ClientNotFound clientNotFound) {
            Client createdClient = clientDtoMapper.mapDtoToModel(createClientDTO);

//            String randomId = UUID.randomUUID().toString();
//            createdUser.setUserId(randomId);
            clientRepository.save(createdClient);
            return clientDtoMapper.mapModelToDto(createdClient);
        }

    }
}
