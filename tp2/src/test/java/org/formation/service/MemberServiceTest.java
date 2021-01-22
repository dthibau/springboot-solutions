package org.formation.service;

import java.util.Date;

import org.formation.model.Document;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberServiceTest {

	@Autowired
	MemberService memberService;
	
	@Test
	void testTransaction() {
		Document doc = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy.doc");
		doc.setUploadedDate(new Date());
		
		memberService.addDocToAllMembers(doc);
	}
	

}
