package com.m2ra.meuprojeto.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.modelo.Restaurante;

@Component
public class CadastroRestaurante {
	
	@PersistenceContext
	EntityManager em;
	
	public List<Restaurante> listar(){
		return em.createQuery("from Restaurante", Restaurante.class).getResultList();
	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return em.merge(restaurante);
	}
	
	public Restaurante buscarRestaurante(Long id) {
		return em.find(Restaurante.class, id);
	}
	
	@Transactional
	public void remover(Restaurante restaurante) {
		
		Restaurante restauranteRemover = this.buscarRestaurante(restaurante.getId());
		
		em.remove(restauranteRemover);
		
	}

}
