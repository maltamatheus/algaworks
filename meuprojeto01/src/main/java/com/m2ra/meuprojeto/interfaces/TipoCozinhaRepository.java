package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.m2ra.meuprojeto.modelo.TipoCozinha;

@Repository
public interface TipoCozinhaRepository {
	
	List<TipoCozinha> listar();
	
	List<TipoCozinha> listarPorNome(String nome);
	
	List<TipoCozinha> listarPorParteDoNome(String parteDoNome);

	TipoCozinha buscar(Long id);
	
	TipoCozinha salvar(TipoCozinha tipoCozinha);
	
	void excluir(Long id);

}
