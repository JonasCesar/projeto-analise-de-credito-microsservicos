package com.cesar.propostaapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_proposta")
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double valorSolicitado;
	
	private int prazoPagamento; // meses
	
	private Boolean aprovada;
	
	private boolean integrada;
	
	private String observacao;
	
	/* O JoinColumn ficará sempre na entidade dominante do relacionamento
	 * (ou seja, a entidade que possui a fk) */
	@OneToOne
	@JoinColumn(name = "id_usuario") 
	private Usuario usuario;

}
