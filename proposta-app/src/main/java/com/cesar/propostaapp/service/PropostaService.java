package com.cesar.propostaapp.service;

import java.util.List;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.beans.factory.annotation.Value;
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

	private String exchange;
	
	public PropostaService(PropostaRepository propostaRepository, 
							NotificacaoService notificacaoService, 
							@Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		
		this.propostaRepository = propostaRepository;
		this.notificacaoService = notificacaoService;
		this.exchange = exchange;
	}
	
	public PropostaResponseDTO criar(PropostaRequestDTO requestDTO) {
		Proposta proposta = PropostaMapper.INSTANCE.convertDtoToProposta(requestDTO);
		
		propostaRepository.save(proposta);			
				
		notificarRabbitMQ(proposta);
		
		return PropostaMapper.INSTANCE.convertEntityToDto(proposta);
	}
	
	private void notificarRabbitMQ(Proposta proposta) {
		// envio do objeto para a exchange.
		// assim que o objeto chegar na exchange, ele será distribuído para as filas ligadas a ela
		
		try {
			notificacaoService.notificar(proposta, exchange);			
		} catch (AmqpConnectException exception) {
			proposta.setIntegrada(false);
			propostaRepository.save(proposta);
		}
	}

	public List<PropostaResponseDTO> obterPropostas() {
		return PropostaMapper.INSTANCE.convertListEntityToListDto(propostaRepository.findAll());		
	}

}
;