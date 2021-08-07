package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.interfaces.FormaPagtoRepository;
import com.m2ra.meuprojeto.modelo.FormaPagto;

@Component
public class FormaPagtoRepositoryImpl implements FormaPagtoRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<FormaPagto> listar() {
		return em.createQuery("from FormaPagto", FormaPagto.class).getResultList();
	}

	@Override
	public FormaPagto buscar(Long id) {
		return em.find(FormaPagto.class, id);
	}

	@Override
	@Transactional
	public FormaPagto salvar(FormaPagto formaPagto) {
		return em.merge(formaPagto);
	}

	@Override
	@Transactional
	public void remover(FormaPagto formaPagto) {
		FormaPagto formaPagtoRemover = this.buscar(formaPagto.getId());
		em.remove(formaPagtoRemover);
	}

}
