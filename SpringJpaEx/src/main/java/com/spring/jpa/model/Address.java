package com.spring.jpa.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Street is required")
	@Size(max = 255, message = "Street must not exceed 255 characters")
	@Column(name = "street", nullable = false)
	private String street;

	@NotBlank(message = "City is required")
	@Size(max = 100, message = "City must not exceed 100 characters")
	@Column(name = "city", nullable = false, length = 100)
	private String city;

	@NotBlank(message = "State is required")
	@Size(max = 100, message = "State must not exceed 100 characters")
	@Column(name = "state", nullable = false, length = 100)
	private String state;

	@NotBlank(message = "Zip code is required")
	@Size(max = 20, message = "Zip code must not exceed 20 characters")
	@Column(name = "zip_code", nullable = false, length = 20)
	private String zipCode;

	@Size(max = 100, message = "Country must not exceed 100 characters")
	@Column(name = "country", length = 100)
	private String country = "USA";

	@Enumerated(EnumType.STRING)
	@Column(name = "address_type", length = 20)
	private AddressType addressType = AddressType.HOME;

	// Many-to-One relationship with User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private User user;

	// Address Type Enum
	public enum AddressType {
		HOME, WORK, BILLING, SHIPPING, OTHER
	}

	// Constructors
	public Address() {
	}

	public Address(String street, String city, String state, String zipCode, String country, AddressType addressType) {
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.country = country;
		this.addressType = addressType;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	// toString method
	@Override
	public String toString() {
		return "Address{" + "id=" + id + ", street='" + street + '\'' + ", city='" + city + '\'' + ", state='" + state
				+ '\'' + ", zipCode='" + zipCode + '\'' + ", country='" + country + '\'' + ", addressType="
				+ addressType + '}';
	}
}
