package com.cesar.propostaapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		return ResponseEntity.ok(response);
	}
	
}
