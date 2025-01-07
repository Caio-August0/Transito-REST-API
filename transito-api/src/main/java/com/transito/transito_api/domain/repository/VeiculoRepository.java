package com.transito.transito_api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.transito.transito_api.domain.model.Propietario;
import com.transito.transito_api.domain.model.Veiculo;

@Repository
public interface VeiculoRepository 
	extends JpaRepository<Veiculo, String>{
	
	boolean existsByPlaca(String placa);
	boolean existsByPropietario(Propietario propietario);
	List<Veiculo> findByPlacaContaining(String placa);
}
