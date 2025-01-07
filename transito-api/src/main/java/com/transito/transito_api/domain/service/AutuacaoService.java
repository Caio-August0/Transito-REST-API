package com.transito.transito_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transito.transito_api.domain.exception.NegotionException;
import com.transito.transito_api.domain.model.Autuacao;
import com.transito.transito_api.domain.repository.VeiculoRepository;

@Service
public class AutuacaoService {
	
	private final VeiculoRepository veiculoRepository;

	public AutuacaoService(VeiculoRepository veiculoRepository) {
		this.veiculoRepository = veiculoRepository;
	}

	public List<Autuacao> listar(String placa) {
		return veiculoRepository.findById(placa)
		.map(veiculo -> veiculo.getAutuacoes())
		.orElseThrow(() -> new NegotionException("Veículo inexistente na base de dados."));
	}
	
	@Transactional
	public Autuacao registrar(String placa, Autuacao autuacao) {
		veiculoRepository.findById(placa)
			.ifPresentOrElse(veiculo -> {
				veiculo.registarAutuacao(autuacao);
			}, () -> { 
				throw new NegotionException("Veículo inexistente");
			});
		return autuacao;
	}
}
