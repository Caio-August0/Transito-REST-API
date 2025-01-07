package com.transito.transito_api.api.representatiom_model.input;

import com.transito.transito_api.api.representatiom_model.input.propietario.PropietarioResumeInputModel;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class VeiculoInputModel {
	
	@Id 
	@Pattern(regexp = "[A-Z]{3}[0-9][0-9A-Z]{2}")
	private String placa;	
	
	@NotBlank
	@Column
	private String marca;
	
	@NotBlank
	@Column
	private String modelo;
	
	@NotNull
	@Valid
	private PropietarioResumeInputModel propietario;
	

	
	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public PropietarioResumeInputModel getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioResumeInputModel propietario) {
		this.propietario = propietario;
	}
	
	

}
