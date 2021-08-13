package com.m2ra.meuprojeto.interfaces.imp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.m2ra.meuprojeto.interfaces.RestauranteRepositoryCustomizado;
import com.m2ra.meuprojeto.modelo.Restaurante;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustomizado {
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Restaurante> consultar(String nome, BigDecimal txFreteMenor, BigDecimal txFreteMaior){
		
		StringBuilder jpql = new StringBuilder("from Restaurante where 1 = 1 ");
		
		HashMap<String, Object> parametros = new HashMap<String,Object>();
		
		if (StringUtils.hasText(nome)) {
			jpql.append(" and nome like :nome ");
			parametros.put("nome", "%" + nome + "%");
		}
		
		if (txFreteMenor != null) {
			jpql.append(" and taxaFrete >= :txFreteMenor ");
			parametros.put("txFreteMenor", txFreteMenor);
		}
		
		if(txFreteMaior != null) {
			jpql.append(" and taxaFrente <= :txFreteMaior ");
			parametros.put("txFreteMaior", txFreteMaior);
		}
		
		TypedQuery<Restaurante> query = em.createQuery(jpql.toString(),Restaurante.class);
		
		parametros.forEach((parametro,valorInputado) -> query.setParameter(parametro, valorInputado));
		
		return query.getResultList();
		
	}

}
