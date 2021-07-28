package org.formation;

import org.formation.service.RestService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Tp3ClientApplicationTests {

	@Autowired
	RestService restService;
	
	
	@Test
	void contextLoads() {
		
		System.out.println(restService.getMember(1l));
	}

}
