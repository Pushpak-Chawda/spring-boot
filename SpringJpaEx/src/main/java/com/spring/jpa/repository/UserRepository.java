package com.spring.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.jpa.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	// Find user by email
    Optional<User> findByEmail(String email);
    
    // Find users by first name (case insensitive)
    List<User> findByFirstNameIgnoreCase(String firstName);
    
    // Find users by last name (case insensitive)
    List<User> findByLastNameIgnoreCase(String lastName);
    
    // Find users by age range
    List<User> findByAgeBetween(Integer minAge, Integer maxAge);
    
    // Custom query to find users with addresses
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.addresses")
    List<User> findAllUsersWithAddresses();
    
    // Custom query to find users by city
    @Query("SELECT DISTINCT u FROM User u JOIN u.addresses a WHERE a.city = :city")
    List<User> findUsersByCity(@Param("city") String city);
    
    // Check if email exists
    boolean existsByEmail(String email);

}
