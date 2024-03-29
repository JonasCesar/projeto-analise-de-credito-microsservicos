package com.cesar.analisecredito.service.strategy.impl;

import java.util.Random;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cesar.analisecredito.MensagemConstante;
import com.cesar.analisecredito.domain.Proposta;
import com.cesar.analisecredito.exceptions.StrategyException;
import com.cesar.analisecredito.service.strategy.CalculoPonto;

@Order(1)
@Component
public class NomeNegativadoImpl implements CalculoPonto {

	@Override
	public int calcular(Proposta proposta) {
		if(nomeNegativado()) {
			throw new StrategyException(String.format(MensagemConstante.CLIENTE_NEGATIVADO, proposta.getUsuario().getNome()));
		}
		return 100;
	}
	
	private boolean nomeNegativado() {
		return new Random().nextBoolean();
	}

}
