package com.spring.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpa.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	
	// Find addresses by user ID
    List<Address> findByUserId(Long userId);
    
    // Find addresses by city
    List<Address> findByCityIgnoreCase(String city);
    
    // Find addresses by state
    List<Address> findByStateIgnoreCase(String state);
    
    // Find addresses by zip code
    List<Address> findByZipCode(String zipCode);
    
    // Find addresses by type
    List<Address> findByAddressType(Address.AddressType addressType);
    
    // Custom query to find addresses with user information
    @Query("SELECT a FROM Address a JOIN FETCH a.user WHERE a.city = :city")
    List<Address> findAddressesWithUserByCity(@Param("city") String city);

}
