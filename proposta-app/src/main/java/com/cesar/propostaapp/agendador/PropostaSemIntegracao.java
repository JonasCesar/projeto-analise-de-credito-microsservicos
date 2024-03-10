package com.cesar.propostaapp.agendador;

import org.springframework.amqp.AmqpConnectException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cesar.propostaapp.repository.PropostaRepository;
import com.cesar.propostaapp.service.NotificacaoRabbitMQService;

@Component
public class PropostaSemIntegracao {
	
	private PropostaRepository propostaRepository;
	
	private NotificacaoRabbitMQService notificacaoRabbitMQService;
	
	private String exchange;
	
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
				proposta.setIntegrada(true);
				propostaRepository.save(proposta);
			}catch (AmqpConnectException exception) {
				System.err.println("Erro ao tentar enviar a proposta para a fila novamente.");
			}			
		});
	}
}
