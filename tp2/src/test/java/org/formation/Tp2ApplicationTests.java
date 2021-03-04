package org.formation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.formation.model.Member;
import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, 
properties = {"security.user.name= user", "security.user.password= secret"})
class Tp2ApplicationTests {

	@Autowired
	RestTemplateBuilder restTemplateBuilder;
	
	private RestTemplate restTemplate;
	
	@BeforeEach
	public void setup() {
		this.restTemplate = restTemplateBuilder.basicAuthentication("dthibau@wmmod.com", "secret").rootUri("http://localhost:8080").build();
	}
	
	@Test
	void findById() {
		
		Member m = restTemplate.getForObject("/api/members/1", Member.class);
		
		assertAll(() -> assertEquals(1, m.getId()),
				  () -> assertTrue(m.getDocuments().isEmpty()));
		
	}

}
