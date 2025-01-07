package com.transito.transito_api.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.transito.transito_api.api.assembler.AutuacaoAssembler;
import com.transito.transito_api.api.representatiom_model.input.AutuacaoInputModel;
import com.transito.transito_api.api.representatiom_model.output.AutuacaoRepresentationModel;
import com.transito.transito_api.domain.service.AutuacaoService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class AutuacaoController {
	
	private final AutuacaoService autuacaoService;
	private final AutuacaoAssembler autuacaoAssembler;
	
	public AutuacaoController(AutuacaoService autuacaoService,
			AutuacaoAssembler autuacaoAssembler) {
		
		this.autuacaoService = autuacaoService;
		this.autuacaoAssembler = autuacaoAssembler;
	}

	@GetMapping("/veiculos/autuacoes/{placa}")
	public ResponseEntity<List<AutuacaoRepresentationModel>> listar(@PathVariable String placa) {
		return ResponseEntity.ok(
				autuacaoAssembler.toColletionModel(
				autuacaoService.listar(placa)));
	}
	
	@PostMapping("/veiculos/autuar/{placa}")//Sub-recurso de veiculo
	public ResponseEntity<AutuacaoRepresentationModel> registrar(@PathVariable String placa,
			@Valid @RequestBody AutuacaoInputModel autuacaoInputModel) {
		
		return ResponseEntity.status(HttpStatus.CREATED).body(
				autuacaoAssembler.toModel(
						autuacaoService.registrar(
						placa, autuacaoAssembler.toEntity(autuacaoInputModel)))	);
	}
	

	
}
