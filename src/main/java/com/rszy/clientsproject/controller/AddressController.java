package com.rszy.clientsproject.controller;


import com.rszy.clientsproject.dto.AddressDTO;
import com.rszy.clientsproject.dto.CreateAddressDTO;
import com.rszy.clientsproject.exeptions.AddressAlreadyExist;
import com.rszy.clientsproject.exeptions.AddressDataInvalid;
import com.rszy.clientsproject.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    private AddressDTO createAddress(@RequestBody CreateAddressDTO createAddressDTO) throws AddressAlreadyExist, AddressDataInvalid {
        return addressService.addAddress(createAddressDTO);
    }
}
