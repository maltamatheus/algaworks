package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.m2ra.meuprojeto.interfaces.TipoCozinhaRepository;
import com.m2ra.meuprojeto.modelo.TipoCozinha;

/**
 * 
 * @author logan
 * 
 * Implementação da interface TipoCozinhaRepository
 * Descontinuada em função da herança na interface TipoCozinhaRepository
 * A interface TipoCozinhaRepository passa a herdar a interface JpaRepository
 * que contém os métodos para manipulação de persistência dos dados
 * 
 * Esta classe foi mantida para fins de histórico e estudos
 *
 */
@Repository
public class TipoCozinhaRepositoryImpl {
	
	@PersistenceContext
	EntityManager em;

	public List<TipoCozinha> listar() {
		return em.createQuery("from TipoCozinha", TipoCozinha.class).getResultList();
	}

	public TipoCozinha buscar(Long id) {
		return em.find(TipoCozinha.class, id);
	}

	@Transactional
	public TipoCozinha salvar(TipoCozinha tipoCozinha) {
		return em.merge(tipoCozinha);
	}

	@Transactional
	public void excluir(Long id) {
		TipoCozinha tipoCozinha = this.buscar(id);
		if (tipoCozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		em.remove(tipoCozinha);
	}

	public List<TipoCozinha> listarPorNome(String nome) {
		return em.createQuery("from TipoCozinha where nome = :nome",TipoCozinha.class).setParameter("nome", nome)
				.getResultList();
	}

	public List<TipoCozinha> listarPorParteDoNome(String parteDoNome) {
		return em.createQuery("from TipoCozinha where nome like :nome ", TipoCozinha.class)
				.setParameter("nome", "%"+parteDoNome+"%")
				.getResultList();
	}
}
