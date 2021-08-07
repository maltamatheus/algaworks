package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.interfaces.PermissaoRepository;
import com.m2ra.meuprojeto.modelo.Permissao;

@Component
public class PermissaoRepositoryImpl implements PermissaoRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Permissao> listar() {
		return em.createQuery("from Permissao", Permissao.class).getResultList();
	}

	@Override
	public Permissao buscar(Long id) {
		return em.find(Permissao.class, id);
	}

	@Override
	@Transactional
	public Permissao salvar(Permissao permissao) {
		return em.merge(permissao);
	}

	@Override
	@Transactional
	public void remover(Permissao permissao) {
		Permissao permissaoRemover = this.buscar(permissao.getId());
		em.remove(permissaoRemover);
	}

}
