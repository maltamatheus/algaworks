package com.m2ra.meuprojeto.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2ra.meuprojeto.interfaces.RestauranteRepository;
import com.m2ra.meuprojeto.interfaces.TipoCozinhaRepository;
import com.m2ra.meuprojeto.machines.CadastroTipoCozinhaEngine;
import com.m2ra.meuprojeto.modelo.Restaurante;
import com.m2ra.meuprojeto.modelo.TipoCozinha;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeEmUsoException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;
import com.m2ra.meuprojeto.modelosretorno.TipoCozinhaXmlWrapper;

@RestController
@RequestMapping("/tiposcozinha")
public class TipoCozinhaController {

	@Autowired
	CadastroTipoCozinhaEngine cadastroTipoCozinha;

	@Autowired
	RestauranteRepository restauranteRepository;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public List<TipoCozinha> listar() {
		System.out.println("Listar com JSON");
		return cadastroTipoCozinha.listar();
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public TipoCozinhaXmlWrapper listarXml() {
		return new TipoCozinhaXmlWrapper(cadastroTipoCozinha.listar());
	}

	@GetMapping("/{tipoCozinhaId}")
	public ResponseEntity<?> buscar(@PathVariable("tipoCozinhaId") Long id) {
		
		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(cadastroTipoCozinha.buscar(id));
			
		} catch (EntidadeNaoEncontradaException e){
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			
		}

	}

	@PostMapping
	public ResponseEntity<TipoCozinha> adicionar(@RequestBody TipoCozinha tipoCozinha) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroTipoCozinha.adicionar(tipoCozinha));
	}

	@PutMapping("/{idTipoCozinha}")
	public ResponseEntity<?> atualizar(@PathVariable Long idTipoCozinha,
			@RequestBody TipoCozinha tipoCozinha) {

		try {
			
			return ResponseEntity.status(HttpStatus.OK).body(cadastroTipoCozinha.atualizar(idTipoCozinha,tipoCozinha));
			
		} catch (EntidadeNaoEncontradaException e) {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@DeleteMapping("/{idTipoCozinha}")
	public ResponseEntity<?> excluir(@PathVariable Long idTipoCozinha) {

		try {

			return ResponseEntity.status(HttpStatus.OK).body(cadastroTipoCozinha.excluir(idTipoCozinha));

		} catch (EntidadeEmUsoException e) {

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

		} catch (EntidadeNaoEncontradaException e) {

			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

		}
	}

}
