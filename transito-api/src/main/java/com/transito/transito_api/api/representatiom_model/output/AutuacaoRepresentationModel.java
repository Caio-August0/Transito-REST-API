package com.transito.transito_api.api.representatiom_model.output;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.transito.transito_api.api.representatiom_model.output.veiculo.VeiculoResumeModel;


public class AutuacaoRepresentationModel {
	
	private String id;
	private VeiculoResumeModel veiculo;
	private String descricao;
	private BigDecimal valorMulta;
	private LocalDateTime dataDaOcorrencia;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public VeiculoResumeModel getVeiculo() {
		return veiculo;
	}
	public void setVeiculo(VeiculoResumeModel veiculo) {
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
	
}
