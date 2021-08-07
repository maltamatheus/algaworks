package com.m2ra.meuprojeto.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.modelo.TipoCozinha;

@Component
public class CadastroTipoCozinha {
	
	@PersistenceContext
	EntityManager em;
	
	public List<TipoCozinha> listar(){
		return em.createQuery("from TipoCozinha", TipoCozinha.class).getResultList();
	}
	
	@Transactional
	public TipoCozinha salvar(TipoCozinha tipoCozinha) {
		return em.merge(tipoCozinha);
	}
	
	public TipoCozinha buscarTipoCozinha(Long id) {
		return em.find(TipoCozinha.class, id);
	}
	
	@Transactional
	public void remover(TipoCozinha tipoCozinha) {
		
		TipoCozinha tipo = this.buscarTipoCozinha(tipoCozinha.getId());
		
		em.remove(tipo);
		
	}

}
