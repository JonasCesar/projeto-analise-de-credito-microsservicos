package com.cesar.propostaapp.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.cesar.propostaapp.dto.PropostaResponseDTO;
import com.cesar.propostaapp.entity.Proposta;
import com.cesar.propostaapp.mapper.PropostaMapper;
import com.cesar.propostaapp.repository.PropostaRepository;
import com.cesar.propostaapp.service.WebSocketService;

@Configuration
public class PropostaConcluidaListener {
	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private WebSocketService webSocketService;
	
	@RabbitListener(queues = "${rabbitmq.queue.proposta.concluida}")
	public void propostaConcluida(Proposta proposta) {
		propostaRepository.save(proposta);
		PropostaResponseDTO responseDto =  PropostaMapper.INSTANCE.convertEntityToDto(proposta);
		webSocketService.notificar(responseDto);
	}

}
