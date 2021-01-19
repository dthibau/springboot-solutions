package org.formation.repository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DocumentRepositoryTest {

	@Autowired
	MemberRepository memberRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	
	@Test
	void testFindByOwner() {
		Member id1 = new Member();
		id1.setId(1l);
		
		 List<Document> documents = documentRepository.findByOwner(id1);
		
		 documents.stream().forEach(System.out::println);
		 	
	}
	
	@Test
	void testFindByOwnerName() {

		
		 List<Document> documents = documentRepository.findByOwnerName("THIBAU");
		
		 documents.stream().forEach(System.out::println);
		 	
	}
	
	@Test
	void testingCascading() {
		int initialMemberCount = memberRepository.findAll().size();
		int initialDocumentCount = documentRepository.findAll().size();
		
		Member newMember = new Member();
		newMember.setAge(18);
		newMember.setEmail("tutu@mail.com");
		newMember.setPassword("secret");

		Document doc = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy.doc");
		doc.setUploadedDate(new Date());
		Document doc2 = new Document();
		doc.setContentType("dummy");
		doc.setName("Dummy2.doc");
		doc.setUploadedDate(new Date());

		newMember.addDocument(doc);
		newMember.addDocument(doc2);
		
		memberRepository.save(newMember);
		
		assertAll("Adding One Member 2 docs",
                () -> assertEquals(initialMemberCount+1,memberRepository.findAll().size()),
                () -> assertEquals(initialDocumentCount+2, documentRepository.findAll().size())
        );
			
	}

	@Test
	void testingFullLoad() {
		
	}
}
