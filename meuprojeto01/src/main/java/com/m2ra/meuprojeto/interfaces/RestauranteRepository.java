package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import com.m2ra.meuprojeto.modelo.Restaurante;

public interface RestauranteRepository extends RestauranteRepositoryCustomizado{
	
	List<Restaurante> listar();
	
	Restaurante buscarRestaurantePorTipoCozinha(Long id);

	Restaurante buscar(Long id);
	
	Restaurante salvar(Restaurante restaurante);
	
	void remover(Restaurante restaurante);

}
