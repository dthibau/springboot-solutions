package org.formation.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientService {

	private final RestTemplate restTemplate;


	@Value("${server.rootUri}")
	String rootUri;
	
	public ClientService(RestTemplateBuilder builder) {
		restTemplate = builder.rootUri("http://localhost:8080/api/members").build();
	}
	

	
	public void loadUser(long id) {
		User user = restTemplate.getForObject("/{id}", User.class, id);
		System.out.println(user);
	}
	
	public void loadAllUser() {
		List<User> users = Arrays.asList(restTemplate.getForObject("/", User[].class));
		System.out.println(users);
	}

	public void createUser(User user) {
		ResponseEntity<User> response = restTemplate.postForEntity("/", user, User.class);
		System.out.println(response.getStatusCodeValue());
		System.out.println(response.getBody());
	}
}