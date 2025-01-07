package com.transito.transito_api.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transito.transito_api.domain.exception.NegotionException;
import com.transito.transito_api.domain.model.Propietario;
import com.transito.transito_api.domain.repository.PropietarioRepository;
import com.transito.transito_api.domain.repository.VeiculoRepository;

@Service
public class PropietarioService {

	private final PropietarioRepository propietarioRepository;
	private final VeiculoRepository veiculoRepository;

	public PropietarioService(PropietarioRepository propietarioRepository,
			VeiculoRepository veiculoRepository) {
		this.propietarioRepository = propietarioRepository;
		this.veiculoRepository = veiculoRepository;
	}

	public List<Propietario> listar() {
		return propietarioRepository.findAll();
	}
	
	public Propietario buscar(String cpf) {
		return propietarioRepository.findById(cpf)
				.orElseThrow(() -> new NegotionException(
						"Propietário inexistente na base dados."
						+ "Não foi possivel encontra-lo"));
	}
	
	@Transactional
	public Propietario salvar(Propietario propietarioParamer) {
		
		propietarioRepository.findByCpf(propietarioParamer.getCpf())
		.ifPresent(propietario -> {
			throw new NegotionException(
					"Propietário existente na base dados. "
							+ "Caso Necesário atualize o seu cadastro");
		});
		
		propietarioRepository
		    .findByEmail(propietarioParamer.getEmail())
		    .ifPresent(propietario -> {
		    	throw new NegotionException(
		    			"Já existe um propietário cadastrado com esse email");
		    	});

		return propietarioRepository.save(propietarioParamer);

	}
	
	@Transactional
	public Propietario atualizar(Propietario propietarioParamer) {
		
		Propietario propietario = buscar(propietarioParamer.getCpf());

		//Caso não haja alterações
		if (propietario.isIguais(propietarioParamer)) {
			throw new NegotionException(
					"Para atualizar insira novos credenciais");
		}
		
		propietarioRepository
			.findByEmail(propietarioParamer.getEmail())
			.filter(propEmail -> !propEmail.equals(propietarioParamer))		
		    .ifPresent(prop -> {
		        throw new NegotionException(
		        		"Já existe um proprietário cadastrado com esse email");
		    	});
		
		BeanUtils.copyProperties(propietarioParamer,propietario,"cpf");
		return propietarioRepository.save(propietario);
		
	}
	@Transactional
	public void deletar(String cpf) {
	  Optional.of(buscar(cpf))
	  	.filter(propietario -> {
			return veiculoRepository.existsByPropietario(propietario);
	  	})// Se nã existir dentro da tabela Veiculo
	  	.ifPresentOrElse(propietario -> {
	  		throw new NegotionException("Quebra de integridade da Foreign Key");
	  		},() -> propietarioRepository.deleteById(cpf));
	}
}
