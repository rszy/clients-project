package com.rszy.clientsproject.mapper;

import com.rszy.clientsproject.dto.AddressDTO;
import com.rszy.clientsproject.dto.CreateAddressDTO;
import com.rszy.clientsproject.dto.CreateClientDTO;
import com.rszy.clientsproject.model.Address;
import com.rszy.clientsproject.model.Client;
import org.springframework.stereotype.Service;

@Service
public class AddressDtoMapper {

    public AddressDTO mapModelToDto (Address address) {
        return new AddressDTO(address.getAddressId(),
                address.getStreet(),
                address.getStreetNumber(),
                address.getApartmentNumber(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry(),
                address.getClient());
    }

    public  Address mapDtoToModel(CreateAddressDTO createAddressDTO) {
        return new Address(null,
                createAddressDTO.getStreetNumber(),
                createAddressDTO.getApartmentNumber(),
                createAddressDTO.getCity(),
                createAddressDTO.getPostalCode(),
                createAddressDTO.getCountry(),
                new Client());
    }
}
