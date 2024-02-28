package com.cesar.propostaapp.service;

import org.springframework.stereotype.Service;

import com.cesar.propostaapp.dto.PropostaRequestDTO;
import com.cesar.propostaapp.dto.PropostaResponseDTO;
import com.cesar.propostaapp.entity.Proposta;
import com.cesar.propostaapp.repository.PropostaRepository;

@Service
public class PropostaService {
	
	private PropostaRepository propostaRepository;
	
	public PropostaService(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}
	
	public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
		propostaRepository.save(new Proposta());
		return null;
	}

}
