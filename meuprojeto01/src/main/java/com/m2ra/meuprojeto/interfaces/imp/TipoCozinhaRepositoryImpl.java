package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.m2ra.meuprojeto.interfaces.TipoCozinhaRepositoryCustomizado;
import com.m2ra.meuprojeto.modelo.TipoCozinha;

@Repository
public class TipoCozinhaRepositoryImpl implements TipoCozinhaRepositoryCustomizado {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<TipoCozinha> consultar(String nome) {
		String jpql = "from TipoCozinha where nome like :nome";
		
		return em.createQuery(jpql, TipoCozinha.class).setParameter("nome", "%" + nome + "%").getResultList();
	}

}
