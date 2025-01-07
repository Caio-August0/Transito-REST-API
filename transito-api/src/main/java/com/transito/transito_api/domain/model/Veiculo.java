package com.transito.transito_api.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.transito.transito_api.domain.exception.ApreensaoVeiculoException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Veiculo {
	
	@Id 
	private String placa;	
	
	private String marca;
	private String modelo;

	@ManyToOne
	private Propietario propietario;
	
	@OneToMany(mappedBy = "veiculo", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Autuacao> autuacoes = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusVeiculo statusVeiculo;
	
	private LocalDateTime dataCadastro;
	private LocalDateTime dataApreensao;
	
	public void cadastrarVeiculo(Propietario propietario) {
		setPropietario(propietario);
		setDataCadastro(LocalDateTime.now());
		setStatusVeiculo(StatusVeiculo.REGULAR);
		setDataApreensao(null);
	}
	
	 
	public void registarAutuacao(Autuacao autuacao) {
		autuacao.setDataDaOcorrencia(LocalDateTime.now());
		autuacao.setVeiculo(this);
		this.getAutuacoes().add(autuacao);
	}

	public void apreender() {
		if (isApreendido()) 
			throw new ApreensaoVeiculoException("O veículo já está apreendido."
					+ "Não foi possível apreende-lo.");
			
		this.setStatusVeiculo(StatusVeiculo.APREENDIDO);
		this.setDataApreensao(LocalDateTime.now());
	}
	
	public void desapreender() {
		if (!isApreendido())
			throw new ApreensaoVeiculoException("O veículo já está desapreendido."
					+ "Não foi possível desapreender.");
		
		this.setStatusVeiculo(StatusVeiculo.REGULAR);
		this.setDataApreensao(null);
	}
	
	public boolean isApreendido() {
		return this.getStatusVeiculo().equals(StatusVeiculo.APREENDIDO);
	}
	
	
	
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
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
	public StatusVeiculo getStatusVeiculo() {
		return statusVeiculo;
	}
	public void setStatusVeiculo(StatusVeiculo statusVeiculo) {
		this.statusVeiculo = statusVeiculo;
	}
	public Propietario getPropietario() {
		return propietario;
	}
	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}
	public List<Autuacao> getAutuacoes() {
		return autuacoes;
	}
	public void setAutuacoes(List<Autuacao> atuacoes) {
		this.autuacoes = atuacoes;
	}
}
