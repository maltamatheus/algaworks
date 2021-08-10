package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.m2ra.meuprojeto.interfaces.TipoCozinhaRepository;
import com.m2ra.meuprojeto.modelo.TipoCozinha;

@Repository
public class TipoCozinhaRepositoryImpl implements TipoCozinhaRepository {
	
	@PersistenceContext
	EntityManager em;

	@Override
	public List<TipoCozinha> listar() {
		return em.createQuery("from TipoCozinha", TipoCozinha.class).getResultList();
	}

	@Override
	public TipoCozinha buscar(Long id) {
		return em.find(TipoCozinha.class, id);
	}

	@Override
	@Transactional
	public TipoCozinha salvar(TipoCozinha tipoCozinha) {
		return em.merge(tipoCozinha);
	}

	@Override
	@Transactional
	public void excluir(Long id) {
		TipoCozinha tipoCozinha = this.buscar(id);
		if (tipoCozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		em.remove(tipoCozinha);
	}

	@Override
	public List<TipoCozinha> listarPorNome(String nome) {
		return em.createQuery("from TipoCozinha where nome = :nome",TipoCozinha.class).setParameter("nome", nome)
				.getResultList();
	}

	@Override
	public List<TipoCozinha> listarPorParteDoNome(String parteDoNome) {
		return em.createQuery("from TipoCozinha where nome like :nome ", TipoCozinha.class)
				.setParameter("nome", "%"+parteDoNome+"%")
				.getResultList();
	}
}
