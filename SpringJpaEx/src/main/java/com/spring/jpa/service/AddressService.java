package com.spring.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.jpa.model.Address;
import com.spring.jpa.repository.AddressRepository;

@Service
@Transactional

public class AddressService {
	private final AddressRepository addressRepository;

	public AddressService(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	// Create address
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	// Get all addresses
	@Transactional(readOnly = true)
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	// Get address by ID
	@Transactional(readOnly = true)
	public Optional<Address> getAddressById(Long id) {
		return addressRepository.findById(id);
	}

	// Get addresses by user ID
	@Transactional(readOnly = true)
	public List<Address> getAddressesByUserId(Long userId) {
		return addressRepository.findByUserId(userId);
	}

	// Update address
	public Address updateAddress(Long id, Address addressDetails) {
		return addressRepository.findById(id).map(address -> {
			address.setStreet(addressDetails.getStreet());
			address.setCity(addressDetails.getCity());
			address.setState(addressDetails.getState());
			address.setZipCode(addressDetails.getZipCode());
			address.setCountry(addressDetails.getCountry());
			address.setAddressType(addressDetails.getAddressType());
			return addressRepository.save(address);
		}).orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
	}

	// Delete address
	public void deleteAddress(Long id) {
		if (!addressRepository.existsById(id)) {
			throw new RuntimeException("Address not found with id: " + id);
		}
		addressRepository.deleteById(id);
	}

	// Get addresses by city
	@Transactional(readOnly = true)
	public List<Address> getAddressesByCity(String city) {
		return addressRepository.findByCityIgnoreCase(city);
	}

	// Get addresses by type
	@Transactional(readOnly = true)
	public List<Address> getAddressesByType(Address.AddressType addressType) {
		return addressRepository.findByAddressType(addressType);
	}
}
