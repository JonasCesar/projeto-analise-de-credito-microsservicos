package com.cesar.analisecredito.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Proposta {

	private Long id;
	
	private Double valorSolicitado;
	
	private int prazoPagamento; // meses
	
	private Boolean aprovada;
	
	private boolean integrada; // integrado com o rabbitmq
	
	private String observacao;
	
	private Usuario usuario;

}
