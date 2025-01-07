package com.transito.transito_api.api.representatiom_model.output.veiculo;

import com.transito.transito_api.api.representatiom_model.output.propietario.PropietarioResumeModel;

public class VeiculoResumeModel {
	
	private String placa;	
	
	private String marca;

	private String modelo;
	
	private PropietarioResumeModel propietario;	
	
	

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

	public PropietarioResumeModel getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioResumeModel propietario) {
		this.propietario = propietario;
	}


}
