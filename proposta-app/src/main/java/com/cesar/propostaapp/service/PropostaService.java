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
	
	private NotificacaoService notificacaoService;
	
	public PropostaService(PropostaRepository propostaRepository, NotificacaoService notificacaoService) {
		this.propostaRepository = propostaRepository;
		this.notificacaoService = notificacaoService;
	}
	
	public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
		
		propostaRepository.save(proposta);
		
		PropostaResponseDTO response = PropostaMapper.INSTANCE.convertEntityToDto(proposta);
		
		// envio do objeto para a exchange.
		// assim que o objeto chegar na exchange, ele será distribuído para as filas ligadas a ela
		notificacaoService.notificar(response, "proposta-pendente.ex");  
		
		return response;
	}

	public List<PropostaResponseDTO> obterPropostas() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());		
	}

}
;