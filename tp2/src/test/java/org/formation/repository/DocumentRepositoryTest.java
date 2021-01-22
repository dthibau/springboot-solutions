package org.formation.repository;

import java.util.Arrays;
import java.util.List;

import org.formation.model.Document;
import org.formation.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.ApplicationContext;

@DataJpaTest
class DocumentRepositoryTest {
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	ApplicationContext applicationContext;
	
	@BeforeEach
	public void setup() {
		Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
	}
	
	
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
}
