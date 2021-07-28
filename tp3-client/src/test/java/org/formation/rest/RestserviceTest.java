package org.formation.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.formation.model.Member;
import org.formation.service.RestService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestClientTest(RestService.class)
public class RestserviceTest {

	@Autowired
    private MockRestServiceServer server;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
    private RestService restService;
	
	@BeforeEach
    public void setUp() throws Exception {
		Member aMember = new Member();
		aMember.setEmail("d@gmail.com");
		aMember.setNom("Nom");
		aMember.setPrenom("Prenom");
		
        String memberString = 
          objectMapper.writeValueAsString(aMember);
        
        this.server.expect(requestTo("/members/1"))
          .andRespond(withSuccess(memberString, MediaType.APPLICATION_JSON));
    }
	
	@Test
    public void whenCallingGetMember_thenOk() 
      throws Exception {

		assertThat(restService.getMember(1)).extracting("email").isEqualTo("d@gmail.com");

    }
	
}
