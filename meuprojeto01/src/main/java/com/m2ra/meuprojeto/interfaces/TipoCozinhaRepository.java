package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.TipoCozinha;

public interface TipoCozinhaRepository {
	
	List<TipoCozinha> listar();

	TipoCozinha buscar(Long id);
	
	TipoCozinha salvar(TipoCozinha tipoCozinha);
	
	void excluir(Long id);

}
