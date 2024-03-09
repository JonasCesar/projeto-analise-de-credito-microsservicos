package com.cesar.propostaapp.mapper;

import java.text.NumberFormat;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.cesar.propostaapp.dto.PropostaRequestDTO;
import com.cesar.propostaapp.dto.PropostaResponseDTO;
import com.cesar.propostaapp.entity.Proposta;

@Mapper(componentModel = "spring")
public interface PropostaMapper {
	
	PropostaMapper INSTANCE = Mappers.getMapper(PropostaMapper.class); // retorna uma instância da implementação de PropostaMapper
		
	@Mapping(target = "usuario.nome", source = "nome") // "usuario" de "usuario.nome" se refere ao campo usuario dentro de Proposta (classe destino - alvo). "nome" se refere ao campo nome em "PropostaRequestDTO" (classe origem)
	@Mapping(target = "usuario.sobrenome", source = "sobrenome")
	@Mapping(target = "usuario.cpf", source = "cpf")
	@Mapping(target = "usuario.telefone", source = "telefone")
	@Mapping(target = "usuario.renda", source = "renda")
	@Mapping(target= "id", ignore = true)
	@Mapping(target= "aprovada", ignore = true)
	@Mapping(target= "integrada", constant = "true")
	@Mapping(target= "observacao", ignore = true)
	Proposta convertDtoToProposta(PropostaRequestDTO propostaRequestDto);
	
	@Mapping(target = "nome", source = "usuario.nome")
	@Mapping(target = "sobrenome", source = "usuario.sobrenome")
	@Mapping(target = "telefone", source = "usuario.telefone")
	@Mapping(target = "cpf", source = "usuario.cpf")
	@Mapping(target = "renda", source = "usuario.renda")
	@Mapping(target = "valorSolicitadoFmt", expression = "java(setValorSolicitadoFmt(proposta))")
	PropostaResponseDTO convertEntityToDto(Proposta proposta);
	
	List<PropostaResponseDTO> convertListEntityToListDto(Iterable<Proposta> listaProposta);
	
	default String setValorSolicitadoFmt(Proposta proposta) {
		return NumberFormat.getCurrencyInstance().format(proposta.getValorSolicitado());
	}

}
