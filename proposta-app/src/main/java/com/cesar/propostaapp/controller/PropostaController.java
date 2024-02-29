package com.cesar.propostaapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cesar.propostaapp.dto.PropostaRequestDTO;
import com.cesar.propostaapp.dto.PropostaResponseDTO;
import com.cesar.propostaapp.service.PropostaService;

@RestController
@RequestMapping("/proposta")
public class PropostaController {
		
	private PropostaService propostaService;
	
	public PropostaController(PropostaService propostaService) {
		this.propostaService = propostaService;
	}

	@PostMapping
	public ResponseEntity<PropostaResponseDTO> criar(@RequestBody PropostaRequestDTO propostaRequestDTO) {
		PropostaResponseDTO response = propostaService.criar(propostaRequestDTO);
		
		return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest() //retornar no response a url (location) para acessar o recurso criado. E retornando o cod 201 (created)
				.path("/{id}")
				.buildAndExpand(response.getId())
				.toUri())
				.body(response);
				
	}
	
	@GetMapping
	public ResponseEntity<List<PropostaResponseDTO>> obterPropostas(){
		return ResponseEntity.ok(propostaService.obterPropostas());		
	}
	
}
