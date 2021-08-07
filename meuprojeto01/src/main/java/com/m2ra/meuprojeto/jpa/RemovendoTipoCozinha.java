package com.m2ra.meuprojeto.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.m2ra.meuprojeto.Meuprojeto01Application;
import com.m2ra.meuprojeto.modelo.TipoCozinha;

public class RemovendoTipoCozinha {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(Meuprojeto01Application.class)
				                                   .web(WebApplicationType.NONE)
				                                   .run(args);
		
		CadastroTipoCozinha cadastroTipoCozinha = applicationContext.getBean(CadastroTipoCozinha.class);
		
		List<TipoCozinha> lista = cadastroTipoCozinha.listar();
		
		for (TipoCozinha tipoCozinha : lista) {
			System.out.println(tipoCozinha.toString());
		}
		
		TipoCozinha tipoCozinha = new TipoCozinha();
		
		tipoCozinha.setId(7L);
		
		cadastroTipoCozinha.remover(tipoCozinha);
		
		lista = cadastroTipoCozinha.listar();
		
		for (TipoCozinha tipo : lista) {
			System.out.println(tipo.toString());
		}
	}

}
