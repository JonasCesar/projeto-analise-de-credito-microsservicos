package com.cesar.analisecredito.service.strategy;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import com.cesar.analisecredito.domain.Proposta;

@Service
public class NotificacaoRabbitService {
	
	private RabbitTemplate rabbitTemplate;

	public NotificacaoRabbitService(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void notificar(String exchange, Proposta proposta) {
		rabbitTemplate.convertAndSend(exchange, "", proposta);
	}
	

}
