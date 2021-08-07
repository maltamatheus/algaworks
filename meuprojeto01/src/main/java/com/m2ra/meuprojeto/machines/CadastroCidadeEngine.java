package com.m2ra.meuprojeto.machines;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.m2ra.meuprojeto.interfaces.CidadeRepository;
import com.m2ra.meuprojeto.modelo.Cidade;
import com.m2ra.meuprojeto.modelo.EstadoUF;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadePaiNaoEncontradaException;

@Service
public class CadastroCidadeEngine {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private CadastroEstadoUFEngine cadastroUF;

	public List<Cidade> listar() {
		return cidadeRepository.listar();
	}

	public Cidade buscar(Long id) {

		Cidade cidade = cidadeRepository.buscar(id);

		if (cidade == null) {
			throw new EntidadeNaoEncontradaException("Não existe cidade " + id + " no cadastro");
		}

		return cidade;
	}
	
	public Cidade adicionar(Cidade cidade) {

		EstadoUF uf = cadastroUF.buscar(cidade.getEstadoUF().getId());

		return cidadeRepository.salvar(cidade);

	}

	public Cidade atualizar(Long id, Cidade cidade) throws EntidadeNaoEncontradaException {

		Cidade cidadeAtual = this.buscar(id);

		try {
			EstadoUF uf = cadastroUF.buscar(cidade.getEstadoUF().getId());
			
			cidade.setEstadoUF(uf);

			BeanUtils.copyProperties(cidade, cidadeAtual, "id");
			
			return cidadeRepository.salvar(cidadeAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			throw new EntidadePaiNaoEncontradaException("Não existe a UF ID " + cidade.getEstadoUF().getId() + " no cadastro.");
		}
		
	}

	public void excluir(Long id) {
		
		cidadeRepository.remover(this.buscar(id));
		
	}

}
