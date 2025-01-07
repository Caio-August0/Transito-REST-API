package com.transito.transito_api.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.transito.transito_api.api.assembler.PropietarioAssembler;
import com.transito.transito_api.api.representatiom_model.input.propietario.PropietarioInputModel;
import com.transito.transito_api.api.representatiom_model.output.propietario.PropietarioRepresentationModel;
import com.transito.transito_api.domain.service.PropietarioService;

import jakarta.validation.Valid;


@Controller
@ResponseBody
@RequestMapping("/propietarios")
public class PropietarioController {

	private final PropietarioService propietarioService;
	private final PropietarioAssembler propietarioAssembler;
	
	public PropietarioController(PropietarioService propietarioService,
			PropietarioAssembler propietarioAssembler) {
		this.propietarioService = propietarioService;
		this.propietarioAssembler = propietarioAssembler;
	}

	@GetMapping
	public ResponseEntity<List<PropietarioRepresentationModel>> listar(){
		return ResponseEntity.ok(			
				propietarioAssembler.toCollectionModel(
					propietarioService.listar()));
	}
	
	@GetMapping("/{cpf}")
	public ResponseEntity<PropietarioRepresentationModel> consultar(@PathVariable String cpf) {	
		return ResponseEntity.ok(
			propietarioAssembler.toModel(
				propietarioService.buscar(cpf)));
	}
	
	@PostMapping
	public ResponseEntity<PropietarioRepresentationModel> salvar(
			@Valid @RequestBody PropietarioInputModel propietarioInputModel) {
		return ResponseEntity.status(HttpStatus.CREATED).
				body(propietarioAssembler.toModel(
						propietarioService.salvar(
								propietarioAssembler.toEntity(propietarioInputModel))));
	}
	
	@PutMapping
	public ResponseEntity<PropietarioRepresentationModel> atualizar(
			@Valid @RequestBody PropietarioInputModel propietarioInputModel) {
        return ResponseEntity.ok().body(
        		propietarioAssembler.toModel(
        				propietarioService.atualizar(
        						propietarioAssembler.toEntity(propietarioInputModel))));
	}
	
	@DeleteMapping("/{cpf}")
	public ResponseEntity<Void> remover(@PathVariable String cpf) {
		propietarioService.deletar(cpf);
		return ResponseEntity.noContent().build();
	}
}
