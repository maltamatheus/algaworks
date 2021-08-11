package com.m2ra.meuprojeto.machines;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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
		return tipoCozinhaRepository.save(tipoCozinha);
	}

	public String excluir(Long id) {

		TipoCozinha tipoCozinha = this.buscar(id);

		try {

			tipoCozinhaRepository.deleteById(tipoCozinha.getId());

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
		return tipoCozinhaRepository.findAll();
	}

	public TipoCozinha buscar(Long id) {
		return tipoCozinhaRepository.findById(id).get();
	}

	public TipoCozinha atualizar(Long idTipoCozinha, TipoCozinha tipoCozinha) {
		TipoCozinha tipoCozinhaAtual = tipoCozinhaRepository.findById(idTipoCozinha).get();

		if (tipoCozinhaAtual == null) {
			throw new EntidadeNaoEncontradaException("Tipo cozinha id " + idTipoCozinha + " não encontrado");
		}

		BeanUtils.copyProperties(tipoCozinha, tipoCozinhaAtual, "id");

		return tipoCozinhaRepository.save(tipoCozinhaAtual);

	}

	public TipoCozinha buscaTipoCozinhaPorNome(String nome) {
		return tipoCozinhaRepository.findTipoCozinhaByNome(nome);
	}

	public TipoCozinha consultaTipoCozinhaPorNome(String nome) {
		return tipoCozinhaRepository.consultaTipoCozinhaPorNome(nome);
	}

	public List<TipoCozinha> buscaTipoCozinhaPorNomeContendo(String parteDoNome) {
		return tipoCozinhaRepository.findTipoCozinhaByNomeContaining(parteDoNome);
	}

	public List<TipoCozinha> consultaTipoCozinhaPorNomeContendo(String trechoDoNome){
		return tipoCozinhaRepository.consultaTipoCozinhaPorNomeContendo(trechoDoNome);
	}
	
	public List<TipoCozinha> pesquisaTipoCozinhaPorNomeContendo(String trechoDoNome){
		return tipoCozinhaRepository.pesquisaTipoCozinhaPorNomeContendo(trechoDoNome);
	}

}
