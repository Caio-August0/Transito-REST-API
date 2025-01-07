package com.transito.transito_api.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.transito.transito_api.domain.service.ApreensaoVeiculoService;

@RestController
public class ApreensaoVeiculoController {

	private final ApreensaoVeiculoService apreensaoVeiculoService;
	
	public ApreensaoVeiculoController(ApreensaoVeiculoService apreensaoVeiculoService) {
		this.apreensaoVeiculoService = apreensaoVeiculoService;
	}

	@PutMapping("/veiculos/apreender/{placa}")
	public ResponseEntity<Void> apreender(@PathVariable String placa) {
		apreensaoVeiculoService.apreender(placa);
		return ResponseEntity.noContent().build();//Ocorreu tudo bem mas não tem corpo
	}
	
	@DeleteMapping("/veiculos/desapreender/{placa}")
	public ResponseEntity<Void> desapreender(@PathVariable String placa) {
		apreensaoVeiculoService.desapreender(placa);
		return ResponseEntity.noContent().build();//Ocorreu tudo bem mas não tem corpo 
	}
}
