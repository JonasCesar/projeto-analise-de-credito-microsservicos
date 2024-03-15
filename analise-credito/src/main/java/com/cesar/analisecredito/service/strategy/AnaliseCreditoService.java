package com.cesar.analisecredito.service.strategy;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesar.analisecredito.domain.Proposta;

@Service
public class AnaliseCreditoService {
	
	private List<CalculoPonto> calculoPontoList;

	public AnaliseCreditoService(List<CalculoPonto> calculoPontoList) {
		this.calculoPontoList = calculoPontoList;
	}
	
	public void analisar(Proposta proposta) {
		int pontuacao = calculoPontoList.stream().mapToInt(calculo -> calculo.calcular(proposta)).sum();
	}
	
}
