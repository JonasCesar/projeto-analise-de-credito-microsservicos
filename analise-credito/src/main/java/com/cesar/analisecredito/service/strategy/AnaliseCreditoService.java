package com.cesar.analisecredito.service.strategy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesar.analisecredito.domain.Proposta;
import com.cesar.analisecredito.exceptions.StrategyException;

@Service
public class AnaliseCreditoService {
	
	private List<CalculoPonto> calculoPontoList;

	public AnaliseCreditoService(List<CalculoPonto> calculoPontoList) {
		this.calculoPontoList = calculoPontoList;
	}
	
	public void analisar(Proposta proposta) {
		try {
			boolean aprovada = calculoPontoList.stream().mapToInt(calculo -> calculo.calcular(proposta)).sum() > 350;
			proposta.setAprovada(aprovada);
		} catch (StrategyException e) {
			proposta.setAprovada(false);
		}
	}
	
}
