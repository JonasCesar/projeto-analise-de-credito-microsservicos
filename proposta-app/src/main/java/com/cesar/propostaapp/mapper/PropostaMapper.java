package com.cesar.propostaapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.cesar.propostaapp.dto.PropostaRequestDTO;
import com.cesar.propostaapp.entity.Proposta;

@Mapper
public interface PropostaMapper {
		
	@Mapping(target= "usuario.nome", source = "nome") // "usuario" de "usuario.nome" se refere ao campo usuario dentro de Proposta (classe destino - alvo). "nome" se refere ao campo nome em "PropostaRequestDTO" (classe origem)
	@Mapping(target = "usuario.sobrenome", source = "sobrenome")
	@Mapping(target = "usuario.telefone", source = "telefone")
	@Mapping(target = "usuario.cpf", source = "cpf")
	@Mapping(target = "usuario.cpf", source = "cpf")
	@Mapping(target = "usuario.cpf", source = "cpf")
	@Mapping(target= "id", ignore = true)
	@Mapping(target= "aprovada", ignore = true)
	@Mapping(target= "integrada", ignore = true)
	@Mapping(target= "observacao", ignore = true)
	Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDto);

}
