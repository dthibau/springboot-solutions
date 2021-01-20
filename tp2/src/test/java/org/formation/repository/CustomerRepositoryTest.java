package org.formation.repository;

import org.formation.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerRepositoryTest {
	
	@Autowired
	CustomerRepository customerRepository;

	@Test
	void playWithMongo() {
		customerRepository.deleteAll();
		// save a couple of customers
		customerRepository.save(new Customer("Alice", "Smith"));
		customerRepository.save(new Customer("Bob", "Smith"));
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		customerRepository.findAll().stream().forEach(System.out::println);

		System.out.println();
		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(customerRepository.findByFirstName("Alice"));
		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		customerRepository.findByLastName("Smith").forEach(System.out::println);

	}
}
