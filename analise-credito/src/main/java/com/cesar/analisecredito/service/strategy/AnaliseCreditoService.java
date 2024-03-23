package com.cesar.analisecredito.service.strategy;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cesar.analisecredito.domain.Proposta;
import com.cesar.analisecredito.exceptions.StrategyException;

@Service
public class AnaliseCreditoService {
	
	private List<CalculoPonto> calculoPontoList;
	
	private NotificacaoRabbitService notificacaoRabbitService;
	
	@Value("${rabbitmq.propostaconcluida.exchange}")
	private String exchangePropostaConcluida;

	public AnaliseCreditoService(List<CalculoPonto> calculoPontoList, NotificacaoRabbitService notificacaoRabbitService) {
		this.calculoPontoList = calculoPontoList;
		this.notificacaoRabbitService = notificacaoRabbitService;
	}
	
	public void analisar(Proposta proposta) {
		try {
			boolean aprovada = calculoPontoList.stream().mapToInt(calculo -> calculo.calcular(proposta)).sum() > 350;
			proposta.setAprovada(aprovada);
		} catch (StrategyException ex) {
			proposta.setAprovada(false);
			proposta.setObservacao(ex.getMessage());
		}
		
		notificacaoRabbitService.notificar(exchangePropostaConcluida, proposta);
	}
	
}
