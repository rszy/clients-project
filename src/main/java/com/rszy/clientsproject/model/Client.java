package com.rszy.clientsproject.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Client {

    @Id
    private String clientId;
    private String firstName;
    private String lastName;
    private String pesel;
    private String email;
    private String phoneNumber;

    @OneToMany(mappedBy = "client")
    private List<Address> addressList;

    public Client() {
    }

    public Client(String clientId, String firstName, String lastName, String pesel, String email, String phoneNumber, List<Address> addressList) {
        this.clientId = clientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pesel = pesel;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.addressList = addressList;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

}
