package com.transito.transito_api.api.assembler;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.transito.transito_api.api.representatiom_model.input.AutuacaoInputModel;
import com.transito.transito_api.api.representatiom_model.output.AutuacaoRepresentationModel;
import com.transito.transito_api.domain.model.Autuacao;

@Component
public class AutuacaoAssembler {
	
	private final VeiculoAssembler veiculoAssembler;
	private final PropietarioAssembler propietarioAssembler;
	
	public AutuacaoAssembler(VeiculoAssembler veiculoAssembler,
			 PropietarioAssembler propietarioAssembler) {
		
		this.veiculoAssembler = veiculoAssembler;
		this.propietarioAssembler = propietarioAssembler;
	}

	public Autuacao toEntity(AutuacaoInputModel autuacaoInputModel) {
		
		return new Autuacao(
				autuacaoInputModel.getDescricao(),
				autuacaoInputModel.getValorMulta()
				); 
	}

	public AutuacaoRepresentationModel toModel(Autuacao autuacao) {
		
		AutuacaoRepresentationModel autuacaoRepresentationModel =
				new AutuacaoRepresentationModel();
		
		BeanUtils.copyProperties(autuacao, autuacaoRepresentationModel,"veiculo");
		
		// Atualiza veiculo
		autuacaoRepresentationModel.setVeiculo(		
		veiculoAssembler.toModelResume(autuacao.getVeiculo()));
		
		//Atualiza propietario veiculo
		autuacaoRepresentationModel.
		getVeiculo().setPropietario(
				propietarioAssembler.toModelResume(
						autuacao.getVeiculo().getPropietario()));
		
		return autuacaoRepresentationModel;
	}
	
	
	public List<AutuacaoRepresentationModel> toColletionModel(List<Autuacao> autuacoes) {
		return autuacoes.stream().map(autuacao -> this.toModel(autuacao)).toList();
	}
	
}
