package org.formation;

import org.formation.service.ClientService;
import org.formation.service.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tp3ClientApplicationTests {

	@Autowired
	ClientService clientService;
	@Test
	void contextLoads() {
	}
	
	@Test
	void oneUser() {
		clientService.loadUser(1);
	}

	@Test
	void allUsers() {
		clientService.loadAllUser();
	}

	@Test
	void createOneUser() {
		User user = new User();
		user.setEmail(System.currentTimeMillis() + "toto@mail.com");
		user.setPassword("secret");
		clientService.createUser(user);
	}

}
