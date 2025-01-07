package com.transito.transito_api.api.representatiom_model.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class AutuacaoInputModel {
	
	@NotBlank
	private String descricao;
	
	@Positive
	private BigDecimal valorMulta;
	
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
	
}
