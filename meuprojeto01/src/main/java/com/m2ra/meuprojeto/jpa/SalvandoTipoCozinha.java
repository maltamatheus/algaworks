package com.m2ra.meuprojeto.jpa;

import java.util.List;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.m2ra.meuprojeto.Meuprojeto01Application;
import com.m2ra.meuprojeto.modelo.TipoCozinha;

public class SalvandoTipoCozinha {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(Meuprojeto01Application.class)
				                                   .web(WebApplicationType.NONE)
				                                   .run(args);
		
		CadastroTipoCozinha cadastroTipoCozinha = applicationContext.getBean(CadastroTipoCozinha.class);
		
		TipoCozinha tipo01 = new TipoCozinha();
		TipoCozinha tipo02 = new TipoCozinha();
		
		tipo01.setNome("Baiana");
		tipo02.setNome("Gaúcha");
		
		tipo01 = cadastroTipoCozinha.salvar(tipo01);
		tipo02 = cadastroTipoCozinha.salvar(tipo02);
		
		System.out.println(tipo01.toString());
		System.out.println(tipo02.toString());
		
	}

}
