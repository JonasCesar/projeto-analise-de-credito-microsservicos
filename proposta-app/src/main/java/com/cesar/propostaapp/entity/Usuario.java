package com.cesar.propostaapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tb_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String sobrenome;
	private String cpf;
	private String telefone;
	private Double renda;
	
	/* O mappedBy ficará sempre no "lado fraco" (não dominante) do relacionamento
	 * Ou seja no lado que não levará a fk da outra tabeladominante
	 * */
	@OneToOne(mappedBy = "usuario") 
	@JsonBackReference // fica no lado da entidade NÃO dominante (para evitar o problema do recurso infinito na hora da serialização do objeto proposta
	private Proposta proposta;

}
