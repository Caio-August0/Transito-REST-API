package com.transito.transito_api.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.transito.transito_api.domain.exception.EntidadeNaoEncontrada;
import com.transito.transito_api.domain.exception.NegotionException;
import com.transito.transito_api.domain.model.Propietario;
import com.transito.transito_api.domain.model.StatusVeiculo;
import com.transito.transito_api.domain.model.Veiculo;
import com.transito.transito_api.domain.repository.PropietarioRepository;
import com.transito.transito_api.domain.repository.VeiculoRepository;

@Service
public class VeiculoService {

	private final PropietarioRepository propietarioRepository;
	private final VeiculoRepository veiculoRepository;

	public VeiculoService(PropietarioRepository propietarioRepository,
			VeiculoRepository veiculoRepository) {
		
		this.propietarioRepository = propietarioRepository;
		this.veiculoRepository = veiculoRepository;
	}

	public List<Veiculo> listar() {
		return veiculoRepository.findAll();
	}
	
	public Veiculo buscar(String placa) {
		return veiculoRepository.findById(placa)
			.orElseThrow(() -> new EntidadeNaoEncontrada(
					"Veículo inexistente na base dados."
							+ "Não é possível encontra-lo"));
	}
	
	@Transactional
	public Veiculo cadastrar(Veiculo veiculoParamer) {
		
		if(veiculoRepository.existsByPlaca(veiculoParamer.getPlaca())) {
			throw new NegotionException("Veículo existente na base dados.\n"
				+ "Não foi possível cadastrar."
					+ "Caso Necesário atualize o seu cadastro");
		}
		
		Propietario propietario = propietarioRepository.
				findById(veiculoParamer.getPropietario().getCpf()).
					orElseThrow(() -> 
						new NegotionException("Não foi possivel cadastrar o veículo. "
								+ "Propietário não encontrado na base de dados.")
					);
		
		veiculoParamer.cadastrarVeiculo(propietario);
		return veiculoRepository.save(veiculoParamer);
	}
	
	@Transactional
	public Veiculo tranferencia(String placa, String cpf) {
		
		Veiculo veiculo = buscar(placa);
		
		if (veiculo.getPropietario().getCpf().equals(cpf)) {
			throw new NegotionException("Não foi possível realizar a transferência.\n"
					+ "O CPF do futuro propietário deve ser diferente do antigo propietário");
		}

		if(!veiculo.getStatusVeiculo().equals(StatusVeiculo.REGULAR)) {
			throw new NegotionException("Veículo apreendido, não foi"
					+ "possível realizar a tranferência.");
		}
		
		propietarioRepository.findByCpf(cpf)
			.ifPresentOrElse(prop -> {
				veiculo.setPropietario(prop);
			 	veiculoRepository.save(veiculo);
			}, () -> {throw new NegotionException("Propietário não cadastrado."
						+ "Não foi possível realizar a transferência");});
		return veiculo;
	}

}
