package com.spring.reactive.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.reactive.models.Customer;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/")
public class CustomerController {

	@GetMapping("getCustomer")
	public Mono<Customer> getCustomer() {

		return Mono.just(new Customer("Pushpak", "101"));
	}

}
