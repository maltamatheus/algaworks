package com.m2ra.meuprojeto.machines;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.m2ra.meuprojeto.interfaces.CidadeRepository;
import com.m2ra.meuprojeto.interfaces.EstadoUFRepository;
import com.m2ra.meuprojeto.modelo.Cidade;
import com.m2ra.meuprojeto.modelo.EstadoUF;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeEmUsoException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;

@Service
public class CadastroEstadoUFEngine {

	@Autowired
	private EstadoUFRepository estadoUFRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public List<EstadoUF> listar() {
		return estadoUFRepository.listar();
	}

	public EstadoUF buscar(Long id) {

		EstadoUF uf = estadoUFRepository.buscar(id);

		if (uf == null) {
			throw new EntidadeNaoEncontradaException("Não existe UF " + id + " no cadastro");
		}

		return uf;
	}

	public EstadoUF adicionar(EstadoUF uf) {
		return estadoUFRepository.salvar(uf);
	}

	public EstadoUF atualizar(Long id, EstadoUF uf) {

		EstadoUF ufAtual = this.buscar(id);

		BeanUtils.copyProperties(uf, ufAtual, "id");

		return estadoUFRepository.salvar(ufAtual);
	}

	public void excluir(Long id) {
		try {
			estadoUFRepository.remover(this.buscar(id));
		} catch (DataIntegrityViolationException e) {

			Cidade cidade = cidadeRepository.buscarCidadePorEstadoUF(id);

			throw new EntidadeEmUsoException(
					"Esse registro possui registro(s) filho(s) atrelado(s) a ele e não pode(m) ser excluído(s).\nRegistro Filho: "
							+ cidade.toString());
		}
	}

}
