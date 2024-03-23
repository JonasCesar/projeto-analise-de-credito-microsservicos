package com.cesar.analisecredito.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.cesar.analisecredito.domain.Proposta;
import com.cesar.analisecredito.service.strategy.AnaliseCreditoService;

@Configuration
public class PropostaEmAnaliseListener {
	
	private AnaliseCreditoService analiseCreditoService;
	
	public PropostaEmAnaliseListener(AnaliseCreditoService analiseCreditoService) {
		this.analiseCreditoService = analiseCreditoService;
	}
	
	@RabbitListener(queues = "${rabbitmq.queue.proposta.pendente}")
	public void propostaEmAnalise(Proposta proposta) {
		analiseCreditoService.analisar(proposta);
	}

}
