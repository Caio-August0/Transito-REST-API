package com.transito.transito_api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transito.transito_api.domain.model.Autuacao;

@Repository
public interface AutuacaoRepository extends JpaRepository<Autuacao, String> {

}
