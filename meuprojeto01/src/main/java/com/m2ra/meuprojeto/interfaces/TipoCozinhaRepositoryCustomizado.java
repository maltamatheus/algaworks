package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.TipoCozinha;

public interface TipoCozinhaRepositoryCustomizado {
	
	public List<TipoCozinha> consultar(String nome) ;

}