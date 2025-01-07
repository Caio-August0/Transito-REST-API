package com.transito.transito_api.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transito.transito_api.domain.model.Propietario;

@Repository
public interface PropietarioRepository 
	extends JpaRepository<Propietario, String>{
	
	Optional<Propietario> findByCpf(String string);
	List<Propietario> findByNomeContaining(String nome);
	Optional<Propietario> findByEmail(String email);
	
}
