package com.rszy.clientsproject.repository;

import com.rszy.clientsproject.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {


    @Query("select a from Address a where a.street = :street " +
            "AND a.streetNumber = :streetNumber " +
            "AND a.apartmentNumber = :apartmentNumber OR :apartmentNumber IS NULL " +
            "AND a.city = :city AND a.postalCode = :postalCode " +
            "AND a.country = :country")
    Address findTheSameAddress(@Param("street")String street, @Param("streetNumber") String streetNumber,
                               @Param("apartmentNumber") String apartmentNumber, @Param("city")String city,
                               @Param("postalCode") String postalCode,
                               @Param("country") String country);


}
