package com.cesar.propostaapp.agendador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpConnectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cesar.propostaapp.entity.Proposta;
import com.cesar.propostaapp.repository.PropostaRepository;
import com.cesar.propostaapp.service.NotificacaoRabbitMQService;

@Component
public class PropostaSemIntegracao {
	
	private PropostaRepository propostaRepository;
	
	private NotificacaoRabbitMQService notificacaoRabbitMQService;
	
	private String exchange;
	
	private final Logger logger = LoggerFactory.getLogger(PropostaSemIntegracao.class);
	
	public PropostaSemIntegracao(PropostaRepository propostaRepository, 
			NotificacaoRabbitMQService notificacaoRabbitMQService,
			@Value("${rabbitmq.propostapendente.exchange}") String exchange) {
		
		this.propostaRepository = propostaRepository;
		this.notificacaoRabbitMQService = notificacaoRabbitMQService;
		this.exchange = exchange;
	}

	public void buscarPropostasSemIntegracao() {
		propostaRepository.findAllByIntegradaIsFalse().forEach(proposta -> {
			
			try {
				notificacaoRabbitMQService.notificar(proposta, exchange);		
				atualizarProposta(proposta);
			}catch (AmqpConnectException exception) {
				logger.error(exception.getMessage());
			}			
		});
	}
	
	private void atualizarProposta(Proposta proposta) {
		proposta.setIntegrada(true);
		propostaRepository.save(proposta);
	}
}
