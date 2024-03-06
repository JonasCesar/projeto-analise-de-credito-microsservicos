package com.cesar.propostaapp.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cesar.propostaapp.dto.PropostaResponseDTO;

@Service
public class NotificacaoService {
	
	private RabbitTemplate rabbitTemplate;
	
	public NotificacaoService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void notificar(PropostaResponseDTO proposta, String exchange) {
		rabbitTemplate.convertAndSend(exchange, "", proposta); // esse tipo de conversor não tem suporte para trabalhar com objetos (nesse caso, o PropostaResponseDTO
	}

}
