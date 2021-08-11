package com.m2ra.meuprojeto.interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.m2ra.meuprojeto.modelo.TipoCozinha;

@Repository
public interface TipoCozinhaRepository extends JpaRepository<TipoCozinha, Long>{
	
	TipoCozinha findTipoCozinhaByNome(String nome);
	
	@Query("from TipoCozinha where nome = :nome")
	TipoCozinha consultaTipoCozinhaPorNome(String nome);
	
	List<TipoCozinha> findTipoCozinhaByNomeContaining(String parteDoNome);
	
	@Query("from TipoCozinha where nome like %:parteDoNome%" )
	List<TipoCozinha> consultaTipoCozinhaPorNomeContendo(@Param("parteDoNome") String trechoDoNome);
	
	List<TipoCozinha> pesquisaTipoCozinhaPorNomeContendo(String nome);
}
