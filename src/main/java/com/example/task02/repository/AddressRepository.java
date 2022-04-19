package com.example.task02.repository;

import com.example.task02.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsAddressByCityAndHomeAndStreet(String city,String home,String street);
Optional<Address> findByCityAndHomeAndStreet(String city,String home,String street);
}