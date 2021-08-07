package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.EstadoUF;

public interface EstadoUFRepository {
	
	List<EstadoUF> listar();

	EstadoUF buscar(Long id);
	
	EstadoUF salvar(EstadoUF estadoUF);
	
	void remover(EstadoUF estadoUF);

}
