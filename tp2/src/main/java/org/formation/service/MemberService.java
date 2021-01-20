package org.formation.service;

import org.formation.model.Document;
import org.formation.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;
	
	@Transactional
	public void addDocToAllMembers(Document document) {
		memberRepository.findAll().stream().forEach(m -> {
			m.addDocument(document);
			memberRepository.save(m);
		});
	}
	
	@Transactional
	public void addDocToAllMembersWithException(Document document) throws Exception {
		memberRepository.findAll().stream().forEach(m -> {
			m.addDocument(document);
			memberRepository.save(m);
		});
		throw new Exception("Rollback ?");
	}
}
