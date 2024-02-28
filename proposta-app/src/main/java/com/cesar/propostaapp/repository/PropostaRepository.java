package com.cesar.propostaapp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cesar.propostaapp.entity.Proposta;

@Repository
public interface PropostaRepository extends CrudRepository<Proposta, Long> {

}
