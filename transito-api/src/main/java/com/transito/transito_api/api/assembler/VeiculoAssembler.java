package com.transito.transito_api.api.assembler;


import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.transito.transito_api.api.representatiom_model.input.VeiculoInputModel;
import com.transito.transito_api.api.representatiom_model.output.propietario.PropietarioResumeModel;
import com.transito.transito_api.api.representatiom_model.output.veiculo.VeiculoRepresentationModel;
import com.transito.transito_api.api.representatiom_model.output.veiculo.VeiculoResumeModel;
import com.transito.transito_api.domain.model.Propietario;
import com.transito.transito_api.domain.model.Veiculo;

@Component
public class VeiculoAssembler {
	
	public Veiculo toEntity (VeiculoInputModel veiculoInputModel) {
		
		Veiculo veiculo = new Veiculo();
		BeanUtils.copyProperties(veiculoInputModel, veiculo);
		
		veiculo.setPropietario(
				new Propietario(
						veiculoInputModel.getPropietario().getCpf()));
		return veiculo;
	}
	
	
	public VeiculoRepresentationModel toModel(Veiculo veiculo) {
		
		VeiculoRepresentationModel veiculoModel = new VeiculoRepresentationModel();
		BeanUtils.copyProperties(veiculo, veiculoModel);
		//Como as propiedades de propietario não se correspondem elas são ignoradas
		
		veiculoModel.setPropietario(
				new PropietarioResumeModel(
					veiculo.getPropietario().getCpf(),
					veiculo.getPropietario().getNome()
					));

		return veiculoModel;
	}
	

	
	public VeiculoResumeModel toModelResume(Veiculo veiculo) {
		VeiculoResumeModel veiculoResumeModel = new VeiculoResumeModel();
		BeanUtils.copyProperties(veiculo, veiculoResumeModel);
		return veiculoResumeModel;
	}
	
	public List<VeiculoRepresentationModel> toCollectionModel(List<Veiculo> veiculos) {
		return veiculos.stream().map(veiculo -> this.toModel(veiculo)).toList();
	}
}
