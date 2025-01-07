package com.transito.transito_api.api.representatiom_model.input.propietario;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Id;

public class PropietarioResumeInputModel {
	
	@Id
	@CPF
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
}
