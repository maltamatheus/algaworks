package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> listar();

	Permissao buscar(Long id);
	
	Permissao salvar(Permissao permissao);
	
	void remover(Permissao permissao);

}
