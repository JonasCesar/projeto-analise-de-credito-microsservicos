package com.cesar.propostaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesar.propostaapp.dto.PropostaRequestDTO;
import com.cesar.propostaapp.dto.PropostaResponseDTO;
import com.cesar.propostaapp.entity.Proposta;
import com.cesar.propostaapp.mapper.PropostaMapper;
import com.cesar.propostaapp.repository.PropostaRepository;

@Service
public class PropostaService {
	
	private PropostaRepository propostaRepository;
	
	public PropostaService(PropostaRepository propostaRepository) {
		this.propostaRepository = propostaRepository;
	}
	
	public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
		propostaRepository.save(proposta);
		
		return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
	}

	public List<PropostaResponseDTO> obterPropostas() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());		
	}

}
;