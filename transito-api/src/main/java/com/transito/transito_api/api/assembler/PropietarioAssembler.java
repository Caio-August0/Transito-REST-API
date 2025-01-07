package com.transito.transito_api.api.assembler;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.transito.transito_api.api.representatiom_model.input.propietario.PropietarioInputModel;
import com.transito.transito_api.api.representatiom_model.output.propietario.PropietarioRepresentationModel;
import com.transito.transito_api.api.representatiom_model.output.propietario.PropietarioResumeModel;
import com.transito.transito_api.domain.model.Propietario;

@Component
public class PropietarioAssembler {

	public Propietario toEntity(PropietarioInputModel propietarioInputModel) {
		
		Propietario propietario = new Propietario();
		
		BeanUtils.copyProperties(propietarioInputModel, propietario);
		return propietario;
	}
	
	public PropietarioRepresentationModel toModel(Propietario propietario) {
		
		PropietarioRepresentationModel propietarioModel =
				new PropietarioRepresentationModel();
		BeanUtils.copyProperties(propietario, propietarioModel);
		
		return propietarioModel;
	}
	
	
	public PropietarioResumeModel toModelResume(Propietario propietario) {
		
		return new PropietarioResumeModel(propietario.getCpf(), propietario.getNome());
		
	}
	
	
	public List<PropietarioRepresentationModel> toCollectionModel(List<Propietario> propietarios) {
		return propietarios.stream().map(propietario -> toModel(propietario)).toList();
	}
	
}
