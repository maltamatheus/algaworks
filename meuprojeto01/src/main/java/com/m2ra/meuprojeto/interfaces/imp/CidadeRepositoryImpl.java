package com.m2ra.meuprojeto.interfaces.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.interfaces.CidadeRepository;
import com.m2ra.meuprojeto.modelo.Cidade;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Cidade> listar() {
		return em.createQuery("from Cidade", Cidade.class).getResultList();
	}

	@Override
	public Cidade buscar(Long id) {
		return em.find(Cidade.class, id);
	}

	@Override
	public Cidade buscarCidadePorEstadoUF(Long id) {
		return em.createQuery("from Cidade where id_estado_uf = " + id, Cidade.class).getResultList().get(0);
	}
	
	@Override
	@Transactional
	public Cidade salvar(Cidade cidade) {
		return em.merge(cidade);
	}

	@Override
	@Transactional
	public void remover(Cidade cidade) {
		Cidade cidadeRemover = this.buscar(cidade.getId());
		em.remove(cidadeRemover);
	}
}
