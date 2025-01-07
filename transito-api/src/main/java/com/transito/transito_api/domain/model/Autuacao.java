package com.transito.transito_api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Autuacao {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	
	@ManyToOne//"Dono" do mapeamento
	@JoinColumn(name = "placa_veiculo")
	private Veiculo veiculo;
	
	private String descricao;
	private BigDecimal valorMulta;
	private LocalDateTime dataDaOcorrencia;


	
	public Autuacao() {}
	
	public Autuacao(String descricao, BigDecimal valorMulta) {
		this.descricao = descricao;
		this.valorMulta = valorMulta;
	}
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Veiculo getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getValorMulta() {
		return valorMulta;
	}
	public void setValorMulta(BigDecimal valorMulta) {
		this.valorMulta = valorMulta;
	}
	public LocalDateTime getDataDaOcorrencia() {
		return dataDaOcorrencia;
	}
	public void setDataDaOcorrencia(LocalDateTime dataDaOcorrencia) {
		this.dataDaOcorrencia = dataDaOcorrencia;
	}

	@Override
	public String toString() {
		return "Autuacao [id=" + id + ", veiculo=" + veiculo + ", descricao=" + descricao + ", valorMulta=" + valorMulta
				+ ", dataDaOcorrencia=" + dataDaOcorrencia + "]";
	}   
	
	
	
}
