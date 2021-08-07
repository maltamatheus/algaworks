package com.m2ra.meuprojeto.machines;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.m2ra.meuprojeto.interfaces.RestauranteRepository;
import com.m2ra.meuprojeto.interfaces.TipoCozinhaRepository;
import com.m2ra.meuprojeto.modelo.Restaurante;
import com.m2ra.meuprojeto.modelo.TipoCozinha;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeEmUsoException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;

@Service
public class CadastroTipoCozinhaEngine {

	@Autowired
	private TipoCozinhaRepository tipoCozinhaRepository;

	@Autowired
	private RestauranteRepository restauranteRepository;

	public TipoCozinha adicionar(TipoCozinha tipoCozinha) {
		return tipoCozinhaRepository.salvar(tipoCozinha);
	}

	public String excluir(Long id) {

		TipoCozinha tipoCozinha = tipoCozinhaRepository.buscar(id);

		try {

			tipoCozinhaRepository.excluir(id);

			return tipoCozinha.toString() + " excluído com sucesso";

		} catch (EmptyResultDataAccessException e) {

			throw new EntidadeNaoEncontradaException("Tipo Cozinha " + id + " não encontrado");

		} catch (DataIntegrityViolationException e) {

			Restaurante restaurante = restauranteRepository.buscarRestaurantePorTipoCozinha(tipoCozinha.getId());

			throw new EntidadeEmUsoException(
					"Este tipo cozinha possui registro filho atrelado a ele e não pode ser excluído.\nRegistro Filho: "
							+ restaurante.toString());
		}
	}

	public List<TipoCozinha> listar() {
		return tipoCozinhaRepository.listar();
	}

	public TipoCozinha buscar(Long id) {
		return tipoCozinhaRepository.buscar(id);
	}

	public TipoCozinha atualizar(Long idTipoCozinha, TipoCozinha tipoCozinha) {

		TipoCozinha tipoCozinhaAtual = tipoCozinhaRepository.buscar(idTipoCozinha);
		
		if(tipoCozinhaAtual == null) {
			throw new EntidadeNaoEncontradaException("Tipo cozinha id " + idTipoCozinha + " não encontrado");
		}

		BeanUtils.copyProperties(tipoCozinha, tipoCozinhaAtual, "id");

		return tipoCozinhaRepository.salvar(tipoCozinhaAtual);

	}

}
