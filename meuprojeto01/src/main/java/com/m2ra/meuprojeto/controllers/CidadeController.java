package com.m2ra.meuprojeto.controllers;

import java.lang.reflect.Field;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m2ra.meuprojeto.machines.CadastroCidadeEngine;
import com.m2ra.meuprojeto.modelo.Cidade;
import com.m2ra.meuprojeto.modelo.EstadoUF;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadeNaoEncontradaException;
import com.m2ra.meuprojeto.modelo.exceptions.EntidadePaiNaoEncontradaException;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
	
	@Autowired
	private CadastroCidadeEngine cadastroCidade;
	
	@GetMapping
	public ResponseEntity<?> listar(){
		if(cadastroCidade.listar().size() > 0) {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroCidade.listar());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não há cidades cadastradas no momento");
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroCidade.buscar(id));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade){
		return ResponseEntity.status(HttpStatus.CREATED).body(cadastroCidade.adicionar(cidade));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> substituir(@PathVariable Long id, @RequestBody Cidade cidade){
		
		try {
			return ResponseEntity.status(HttpStatus.OK).body(cadastroCidade.atualizar(id, cidade));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (EntidadePaiNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Map<String, Object> campos){
		
		try {
			Cidade cidadeAtual = cadastroCidade.buscar(id);

			merge(campos,cidadeAtual);
			
			return this.substituir(id, cidadeAtual);
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}

	private void merge(Map<String, Object> atributos, Cidade cidade) {
		
		//Ligando um conversor de tipos de atributos
		ObjectMapper conversor = new ObjectMapper();
		
		//Criando uma instância de uma classe com os valores informados
		Cidade cidadeOrigem = conversor.convertValue(atributos, Cidade.class);
		
		//Listando os atributos passados para o patch
		atributos.forEach((atributo,valor) -> {
			
			//Obtendo cada atributo listado e seu valor
			Field campo = ReflectionUtils.findField(Cidade.class, atributo);
			
			//Seta a permissão para acessar valores privados
			campo.setAccessible(true);
			
			//Resgatando cada campo informado da Classe a ser atualizada
			Object campoExtraido = ReflectionUtils.getField(campo, cidadeOrigem);
			
			//Setando os atributos e valores para a classe de destino
			ReflectionUtils.setField(campo, cidade, campoExtraido);
		});
	}
}
