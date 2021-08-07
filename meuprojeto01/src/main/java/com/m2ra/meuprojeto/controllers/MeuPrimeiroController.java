package com.m2ra.meuprojeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.m2ra.meuprojeto.machines.AtivacaoClienteEngine;
import com.m2ra.meuprojeto.modelo.Cliente;

@Controller
public class MeuPrimeiroController {
	
	private AtivacaoClienteEngine ativacaoCliente;
	
	public MeuPrimeiroController(AtivacaoClienteEngine ativacaoCliente) {
		this.ativacaoCliente = ativacaoCliente;
		System.out.println("Ativando o componente AtivacaoClienteMachine");
	}

	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		
		Cliente matheus = new Cliente();
		matheus.setNome("Matheus");
		matheus.setEmail("matheus@m2ra.com");
		matheus.setTelefone("11981332765");
		
		ativacaoCliente.ativarCliente(matheus);
		
		return "Hello";
	}

}
