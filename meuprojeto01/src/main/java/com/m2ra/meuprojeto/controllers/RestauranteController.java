package com.m2ra.meuprojeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.m2ra.meuprojeto.machines.CadastroRestauranteEngine;
import com.m2ra.meuprojeto.modelo.Restaurante;
import com.m2ra.meuprojeto.modelo.exceptions.ChaveNaoEncontradaException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private CadastroRestauranteEngine cadastroRestaurante;
	
	@GetMapping
	@ResponseStatus(value = HttpStatus.OK)
	public List<Restaurante> listar(){
		return cadastroRestaurante.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> buscar(@PathVariable Long id){
		if (cadastroRestaurante.buscar(id) == null) {
			System.out.println("Não existe restaurante cadastrado com id " + id);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(cadastroRestaurante.buscar(id));
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(cadastroRestaurante.adicionar(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroRestaurante.atualizar(id,restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (ChaveNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}

}
