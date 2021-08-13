package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.interfaces.RestauranteRepository;
import com.m2ra.meuprojeto.modelo.Restaurante;

@Component
public class RestauranteRepositoryImp implements RestauranteRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<Restaurante> listar() {
		return em.createQuery("from Restaurante", Restaurante.class).getResultList();
	}

	@Override
	public Restaurante buscar(Long id) {
		return em.find(Restaurante.class, id);
	}

	@Override
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return em.merge(restaurante);
	}

	@Override
	@Transactional
	public void remover(Restaurante restaurante) {
		Restaurante restauranteRemove = this.buscar(restaurante.getId());
		em.remove(restauranteRemove);
	}

	@Override
	public Restaurante buscarRestaurantePorTipoCozinha(Long id) {
		return em.createQuery("from Restaurante where id_tipo_cozinha = " + id, Restaurante.class).getResultList().get(0);
	}

}
