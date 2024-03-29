package org.formation.model;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
	
	public List<Customer> findByLastName(String lastName);
	
	public List<Customer> findByFirstName(String firstName);
	
	public List<Customer> findByFirstNameAndLastName(String firstName, String lastName);
	

}