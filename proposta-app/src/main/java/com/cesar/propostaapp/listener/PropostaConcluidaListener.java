package com.cesar.propostaapp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.cesar.propostaapp.entity.Proposta;
import com.cesar.propostaapp.repository.PropostaRepository;

@Configuration
public class PropostaConcluidaListener {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
	public void propostaConcluida(Proposta proposta) {
		propostaRepository.save(proposta);
	}

}
