package com.m2ra.meuprojeto.machines;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2ra.meuprojeto.interfaces.RestauranteRepository;
import com.m2ra.meuprojeto.interfaces.TipoCozinhaRepository;
import com.m2ra.meuprojeto.modelo.Restaurante;
import com.m2ra.meuprojeto.modelo.TipoCozinha;
import com.m2ra.meuprojeto.modelo.exceptions.ChaveNaoEncontradaException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;

@Service
public class CadastroRestauranteEngine {

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private TipoCozinhaRepository tipoCozinhaRepository;

	public List<Restaurante> listar() {
		return restauranteRepository.listar();
	}

	public Restaurante buscar(Long id) {
		return restauranteRepository.buscar(id);
	}
	
	public Restaurante adicionar(Restaurante restaurante) {
		
		TipoCozinha tipoCozinha = tipoCozinhaRepository.buscar(restaurante.getTipoCozinha().getId());

		if (tipoCozinha == null) {
			throw new EntidadeNaoEncontradaException("Tipo Cozinha não cadastrado");
		}
		
		return restauranteRepository.salvar(restaurante);
	}

	public Restaurante atualizar(Long id, Restaurante restaurante) {

		Restaurante restauranteAtual = restauranteRepository.buscar(id);
		
		if (restauranteAtual == null) {
			throw new EntidadeNaoEncontradaException("Restaurante " + id + " não encontrado");
		}

		TipoCozinha tipoCozinha = tipoCozinhaRepository.buscar(restaurante.getTipoCozinha().getId());
		
		if (tipoCozinha == null) {
			throw new ChaveNaoEncontradaException("Tipo Cozinha " + restaurante.getTipoCozinha().getId() + " não cadastrado");
		}
		
		BeanUtils.copyProperties(restaurante, restauranteAtual, "id");
		
		restauranteAtual.setTipoCozinha(tipoCozinha);
		
		return restauranteRepository.salvar(restauranteAtual);
	}
}
