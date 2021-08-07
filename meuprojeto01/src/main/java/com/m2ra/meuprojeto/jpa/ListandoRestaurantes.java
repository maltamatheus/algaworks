package com.m2ra.meuprojeto.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.m2ra.meuprojeto.Meuprojeto01Application;
import com.m2ra.meuprojeto.modelo.Restaurante;

public class ListandoRestaurantes {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(Meuprojeto01Application.class)
				                                   .web(WebApplicationType.NONE)
				                                   .run(args);
		
		CadastroRestaurante cadastroRestaurante = applicationContext.getBean(CadastroRestaurante.class);
		
		List<Restaurante> listaRestaurante = cadastroRestaurante.listar();
		
		for (Restaurante restaurante : listaRestaurante) {
			
			System.out.println("Restaurante: " + restaurante.getNome() + " - Tipo Cozinha: " + restaurante.getTipoCozinha().getNome());
			
		}
	}

}
