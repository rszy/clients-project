package com.rszy.clientsproject.service;

import com.rszy.clientsproject.dto.AddressDTO;
import com.rszy.clientsproject.dto.CreateAddressDTO;
import com.rszy.clientsproject.exeptions.AddressAlreadyExist;
import com.rszy.clientsproject.exeptions.AddressDataInvalid;
import com.rszy.clientsproject.exeptions.ClientNotFound;
import com.rszy.clientsproject.mapper.AddressDtoMapper;
import com.rszy.clientsproject.model.Address;
import com.rszy.clientsproject.model.Client;
import com.rszy.clientsproject.repository.AddressRepository;
import com.rszy.clientsproject.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    @Autowired
    private AddressDtoMapper addressDtoMapper;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;


    public AddressDTO addAddress (CreateAddressDTO createAddressDTO) throws AddressAlreadyExist, AddressDataInvalid, ClientNotFound {
        if(createAddressDTO.getStreet() == null
                || createAddressDTO.getStreetNumber() == null
                || createAddressDTO.getCity() == null
                || createAddressDTO.getPostalCode() == null
                || createAddressDTO.getCountry() == null){
            throw new AddressDataInvalid();
        }

        if (!createAddressDTO.getPostalCode().matches("\\d{2}-\\d{3}")) {
            throw new AddressDataInvalid();
        }

        Address theSameAddress = addressRepository.findTheSameAddress(createAddressDTO.getStreet(),
                createAddressDTO.getStreetNumber(),
                createAddressDTO.getApartmentNumber(),
                createAddressDTO.getCity(),
                createAddressDTO.getPostalCode(),
                createAddressDTO.getCountry());

        if (theSameAddress != null){
            throw new AddressAlreadyExist();
        }

        Optional<Client> optionalClient = clientRepository.findById(createAddressDTO.getClientId());
        if(optionalClient.isPresent()){
            Address addressToAdd = addressDtoMapper.mapDtoToModel(createAddressDTO);
            String randomId = UUID.randomUUID().toString();
            addressToAdd.setAddressId(randomId);
            addressToAdd.setClient(optionalClient.get());
            Address savedAddress = addressRepository.save(addressToAdd);
            return addressDtoMapper.mapModelToDto(savedAddress);
        }
        throw new ClientNotFound();
    }
}
