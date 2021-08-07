package com.m2ra.meuprojeto.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.m2ra.meuprojeto.interfaces.EstadoUFRepository;
import com.m2ra.meuprojeto.machines.CadastroEstadoUFEngine;
import com.m2ra.meuprojeto.modelo.EstadoUF;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeEmUsoException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;

@RestController
@RequestMapping("/estados")
public class EstadoUFController {
	
	@Autowired
	private CadastroEstadoUFEngine cadastroUF;
	
	@GetMapping
	public List<EstadoUF> listar(){
		return cadastroUF.listar();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroUF.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<EstadoUF> adicionar(@RequestBody EstadoUF estadoUF){
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroUF.adicionar(estadoUF));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody EstadoUF estadoUF){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroUF.atualizar(id, estadoUF));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> excluir(@PathVariable Long id){
		try {
			cadastroUF.excluir(id);
			return ResponseEntity.status(HttpStatus.OK).body("UF ID " + id + " excluída com sucesso");
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

}
