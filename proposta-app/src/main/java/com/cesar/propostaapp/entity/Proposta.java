package com.cesar.propostaapp.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_proposta")
public class Proposta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Double valorSolicitado;
	
	private int prazoPagamento; // meses
	
	private Boolean aprovada;
	
	private boolean integrada; // integrado com o rabbitmq
	
	private String observacao;
	
	/* O JoinColumn ficará sempre na entidade dominante do relacionamento
	 * (ou seja, a entidade que possui a fk) */
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "id_usuario") 
	private Usuario usuario;

}
