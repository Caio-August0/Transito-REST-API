package com.transito.transito_api.domain.service;

import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

@Service
public class ApreensaoVeiculoService {

	private final VeiculoService veiculoService;
	
	public ApreensaoVeiculoService(VeiculoService veiculoService) {
		this.veiculoService = veiculoService;
	}
	
	@Transactional
	public void apreender(String placa) {
		veiculoService.buscar(placa).apreender();
	}
	
	@Transactional
	public void desapreender(String placa) {
		veiculoService.buscar(placa).desapreender();
	}
}
