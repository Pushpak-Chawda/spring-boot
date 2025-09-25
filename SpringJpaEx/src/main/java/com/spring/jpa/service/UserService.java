package com.spring.jpa.service;

import java.util.List;
import java.util.Optional;

import com.spring.jpa.model.Address;
import com.spring.jpa.model.User;
import com.spring.jpa.repository.UserRepository;

import org.springframework.stereotype.Service;
import  org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	// Create a new user
	public User createUser(User user) {
		if (userRepository.existsByEmail(user.getEmail())) {
			throw new RuntimeException("User with email " + user.getEmail() + " already exists");
		}
		return userRepository.save(user);
	}

	// Get all users
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return null;
		//return userRepository.findAll();
	}

	// Get user by ID
	@Transactional(readOnly = true)
	public Optional<User> getUserById(Long id) {
		return userRepository.findById(id);
	}

	// Get user by email
	@Transactional(readOnly = true)
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	// Update user
	public User updateUser(Long id, User userDetails) {
		return userRepository.findById(id).map(user -> {
			user.setFirstName(userDetails.getFirstName());
			user.setLastName(userDetails.getLastName());
			user.setEmail(userDetails.getEmail());
			user.setPhone(userDetails.getPhone());
			user.setAge(userDetails.getAge());
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
	}

	// Delete user
	public void deleteUser(Long id) {
		if (!userRepository.existsById(id)) {
			throw new RuntimeException("User not found with id: " + id);
		}
		userRepository.deleteById(id);
	}

	// Add address to user
	public User addAddressToUser(Long userId, Address address) {
		return userRepository.findById(userId).map(user -> {
			user.addAddress(address);
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
	}

	// Get users with addresses
	@Transactional()
	public List<User> getUsersWithAddresses() {
		return userRepository.findAllUsersWithAddresses();
	}

	// Search users by city
	@Transactional(readOnly = true)
	public List<User> getUsersByCity(String city) {
		return userRepository.findUsersByCity(city);
	}
}
