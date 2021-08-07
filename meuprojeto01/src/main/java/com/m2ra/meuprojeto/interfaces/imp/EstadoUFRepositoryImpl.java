package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.interfaces.EstadoUFRepository;
import com.m2ra.meuprojeto.modelo.EstadoUF;

@Component
public class EstadoUFRepositoryImpl implements EstadoUFRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<EstadoUF> listar() {
		return em.createQuery("from EstadoUF", EstadoUF.class).getResultList();
	}

	@Override
	public EstadoUF buscar(Long id) {
		return em.find(EstadoUF.class, id);
	}

	@Override
	@Transactional
	public EstadoUF salvar(EstadoUF estadoUF) {
		return em.merge(estadoUF);
	}

	@Override
	@Transactional
	public void remover(EstadoUF estadoUF) {
		EstadoUF estadoUFRemover = this.buscar(estadoUF.getId());
		em.remove(estadoUFRemover);
	}

}
