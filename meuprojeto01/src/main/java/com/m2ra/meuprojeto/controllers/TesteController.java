package com.m2ra.meuprojeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.m2ra.meuprojeto.machines.CadastroTipoCozinhaEngine;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CadastroTipoCozinhaEngine cadastroTipoCozinha;

	@GetMapping("/busca-tipocozinha-por-nome")
	public ResponseEntity<?> buscaTipoCozinhaPorNome(@RequestParam String nome) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(cadastroTipoCozinha.buscaTipoCozinhaPorNome(nome));

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma cozinha encontrada");

		}
	}

	@GetMapping("/consulta-tipocozinha-por-nome")
	public ResponseEntity<?> consultaTipoCozinhaPorNome(String nome) {
		try {

			return ResponseEntity.status(HttpStatus.OK).body(cadastroTipoCozinha.consultaTipoCozinhaPorNome(nome));

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhuma cozinha encontrada");

		}
	}

	@GetMapping("/busca-tipocozinha-por-nome-contendo")
	public ResponseEntity<?> buscaTipoCozinhaPorNomeContendo(@RequestParam String parteDoNome) {

		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(cadastroTipoCozinha.buscaTipoCozinhaPorNomeContendo(parteDoNome));
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem cozinhas com esse critério");
		}
	}

	@GetMapping("/consulta-tipocozinha-por-nome-contendo")
	public ResponseEntity<?> consultaTipoCozinhaPorNomeContendo(String trechoDoNome){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(cadastroTipoCozinha.consultaTipoCozinhaPorNomeContendo(trechoDoNome));
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem cozinhas com esse critério");
		}
	}
	
	@GetMapping("/pesquisa-tipocozinha-por-nome-contendo")
	public ResponseEntity<?> pesquisaTipoCozinhaPorNomeContendo(String trechoDoNome){
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(cadastroTipoCozinha.pesquisaTipoCozinhaPorNomeContendo(trechoDoNome));
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não existem cozinhas com esse critério");
		}
	}

}
