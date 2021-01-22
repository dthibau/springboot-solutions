package org.formation.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.formation.model.Member;
import org.formation.repository.DocumentRepository;
import org.formation.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value=MemberRestController.class)
public class MemberRestControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	MemberRepository memberRepository;
	
	@MockBean
	DocumentRepository documentRepository;
	
	Optional<Member> optMember;
	
	@BeforeEach
	public void setUp() {
		
		Member oneMember = new Member();
		oneMember.setId(1l);
		oneMember.setEmail("toto@mail.com");
		
		optMember = Optional.of(oneMember);
	}
	@Test
	@WithMockUser
	public void testGetById() throws Exception {
		given(memberRepository.findById(1l)).willReturn(optMember);
		mockMvc.perform(get("/api/members/1")).andExpect(status().isOk()).andExpect(jsonPath("$.email").value("toto@mail.com"));;
	}

}
