package com.cesar.propostaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.cesar.propostaapp.dto.PropostaResponseDTO;

@Service
public class WebSocketService {
	
	@Autowired
	private SimpMessagingTemplate template;
	
	public void notificar(PropostaResponseDTO proposta) {
		template.convertAndSend("/propostas", proposta);
	}

}
