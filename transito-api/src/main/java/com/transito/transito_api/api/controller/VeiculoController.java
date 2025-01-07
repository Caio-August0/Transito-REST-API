package com.transito.transito_api.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.transito.transito_api.api.assembler.VeiculoAssembler;
import com.transito.transito_api.api.representatiom_model.input.VeiculoInputModel;
import com.transito.transito_api.api.representatiom_model.output.veiculo.VeiculoRepresentationModel;
import com.transito.transito_api.domain.service.VeiculoService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

	private final VeiculoService veiculoService;
	private final VeiculoAssembler veiculoAssembler;
	
	public VeiculoController(VeiculoService veiculoService,
			VeiculoAssembler veiculoAssembler) {
		this.veiculoService = veiculoService;
		this.veiculoAssembler = veiculoAssembler;
	}

	@GetMapping
	public ResponseEntity<List<VeiculoRepresentationModel>> listar() {
		return ResponseEntity.ok(
				veiculoAssembler.toCollectionModel(
						veiculoService.listar()));
	}
	
	@GetMapping("/{placa}")
	public ResponseEntity<VeiculoRepresentationModel> consultar(@Valid @PathVariable String placa) {
		return ResponseEntity.ok(veiculoAssembler.toModel(
					veiculoService.buscar(placa)));
	}
	
	@PostMapping
	public ResponseEntity<VeiculoRepresentationModel> salvar(
			@Valid @RequestBody VeiculoInputModel veiculoInputModel) {
		
		return ResponseEntity.
				status(HttpStatus.CREATED)
					.body(veiculoAssembler.toModel(
							veiculoService.cadastrar(
									veiculoAssembler.toEntity(veiculoInputModel))));
	}
	
	@PutMapping("/transferir")
	public ResponseEntity<VeiculoRepresentationModel> transferir(
			@Valid @RequestParam String placa, @RequestParam String cpf) {
		return ResponseEntity.ok(
				veiculoAssembler.toModel(
						veiculoService.tranferencia(placa, cpf)));
		 
	}
	
}
