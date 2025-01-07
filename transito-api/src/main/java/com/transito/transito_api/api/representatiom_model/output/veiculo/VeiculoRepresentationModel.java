package com.transito.transito_api.api.representatiom_model.output.veiculo;

import java.time.LocalDateTime;

import com.transito.transito_api.api.representatiom_model.output.propietario.PropietarioResumeModel;
import com.transito.transito_api.domain.model.StatusVeiculo;


public class VeiculoRepresentationModel {
	
	private String placa;	
	
	private String marca;

	private String modelo;

	private StatusVeiculo statusVeiculo;
	
	private LocalDateTime dataCadastro;

	private LocalDateTime dataApreensao;

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

	public StatusVeiculo getStatusVeiculo() {
		return statusVeiculo;
	}

	public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
		this.statusVeiculo = statusVeiculo;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public LocalDateTime getDataApreensao() {
		return dataApreensao;
	}

	public void setDataApreensao(LocalDateTime dataApreensao) {
		this.dataApreensao = dataApreensao;
	}

	public PropietarioResumeModel getPropietario() {
		return propietario;
	}

	public void setPropietario(PropietarioResumeModel propietario) {
		this.propietario = propietario;
	}
	
	
	
}
