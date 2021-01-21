package org.formation.controller;

import java.util.List;

import javax.validation.Valid;

import org.formation.model.Member;
import org.formation.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/members")
public class MemberRestController {

	private final MemberRepository memberRepository;

	public MemberRestController(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	@GetMapping
	public List<Member> getAll() throws MemberNotFoundException {

		return memberRepository.findAll();

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> getById(@PathVariable long id) throws MemberNotFoundException {

		return ResponseEntity.ok()
				.body(memberRepository.findById(id).orElseThrow(
						() -> new MemberNotFoundException("Id " + id)));

	}
	
	@GetMapping("full/{id}")
	public MemberWithDocumentDto getFullById(@PathVariable long id) throws MemberNotFoundException {
		
		Member member = memberRepository.fullLoad(id);

		return new MemberWithDocumentDto(member);

	}
	
	@GetMapping("/search")
	public List<Member> search(@RequestParam String q) throws MemberNotFoundException {

		return memberRepository.findByNomContainsIgnoreCase(q);

	}
	
	@Operation(summary = "Créer un Membre", description = "Ne pas saisir id, nomComplet et registeredDate" )
	@ApiResponses(value = { 
	  @ApiResponse(responseCode = "201", description = "Le membre est créé", 
	    content = { @Content(mediaType = "application/json", 
	      schema = @Schema(implementation = Member.class)) }) })
	@PostMapping
	public ResponseEntity<Member> create(@Valid @RequestBody Member member) {

		member.setId(0l);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(memberRepository.save(member));

	}
	
	@PutMapping
	public ResponseEntity<Member> replace(@Valid @RequestBody Member member) throws MemberNotFoundException {
		memberRepository.findById(member.getId()).orElseThrow(
				() -> new MemberNotFoundException("Id " + member.getId()));
		
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(member));

	}
	@PatchMapping
	public ResponseEntity<Member> partialUpdate(@RequestBody Member member) throws MemberNotFoundException {
		Member originalMember = memberRepository.findById(member.getId()).orElseThrow(
				() -> new MemberNotFoundException("Id " + member.getId()));
		if ( member.getEmail() != null ) {
			originalMember.setEmail(member.getEmail());
		}
		if ( member.getNom() != null ) {
			originalMember.setNom(member.getNom());
		}
		if ( member.getPassword() != null ) {
			originalMember.setEmail(member.getPassword());
		}
		if ( member.getPrenom() != null ) {
			originalMember.setPrenom(member.getPrenom());
		}
		if ( member.getRegisteredDate() != null ) {
			originalMember.setRegisteredDate(member.getRegisteredDate());
		}

		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(memberRepository.save(originalMember));

	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable long id) throws MemberNotFoundException {
		memberRepository.findById(id).orElseThrow(
				() -> new MemberNotFoundException("Id " + id));
		memberRepository.deleteById(id);
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();


	}

}
