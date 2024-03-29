package com.cesar.analisecredito.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cesar.analisecredito.MensagemConstante;
import com.cesar.analisecredito.domain.Proposta;
import com.cesar.analisecredito.exceptions.StrategyException;
import com.cesar.analisecredito.service.strategy.CalculoPonto;

@Order(2)
@Component
public class PontuacaoScoreImpl implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		int score = score();
		
		if(score <= 200) {
			throw new StrategyException(String.format(MensagemConstante.PONTUACAO_SERASA_BAIXA, proposta.getUsuario().getNome()));
		} else if(score <= 400) {
			return 150;
		} else if(score <= 600) {
			return 180;
		}
		return 220;
	}
	
	private int score() {
		return new Random().nextInt(0, 1000);
	}

}
