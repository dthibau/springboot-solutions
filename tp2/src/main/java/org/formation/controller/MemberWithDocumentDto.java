package org.formation.controller;

import java.io.Serializable;
import java.util.Set;

import org.formation.model.Document;
import org.formation.model.Member;

public class MemberWithDocumentDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Member member;
	
	public MemberWithDocumentDto(Member member) {
		this.member = member;
	}
	
	public String getNomComplet() {
		return member.getNomComplet();
	}
	public Set<Document> getDocuments() {
		return member.getDocuments();
	}
}
