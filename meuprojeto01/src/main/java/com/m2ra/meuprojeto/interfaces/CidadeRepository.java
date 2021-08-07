package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.Cidade;

public interface CidadeRepository {
	
	List<Cidade> listar();
	
	Cidade buscar(Long id);
	
	Cidade buscarCidadePorEstadoUF(Long id);
	
	Cidade salvar(Cidade cidade);
	
	void remover(Cidade cidade);

}
