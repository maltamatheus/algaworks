package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.FormaPagto;

public interface FormaPagtoRepository {
	
	List<FormaPagto> listar();

	FormaPagto buscar(Long id);
	
	FormaPagto salvar(FormaPagto formaPagto);
	
	void remover(FormaPagto formaPagto);

}
