package com.m2ra.meuprojeto.machines;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.anotacoes.TipoDoNotificador;
import com.m2ra.meuprojeto.enums.NivelUrgencia;
import com.m2ra.meuprojeto.interfaces.Notificador;
import com.m2ra.meuprojeto.modelo.Cliente;

@Component
public class AtivacaoClienteEngine {

	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	@Autowired
	private Notificador notificador;
	
	@PostConstruct
	public void init() {
		System.out.println("Executou o Init");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("Executou o Destroy");
	}
	
	public void ativarCliente(Cliente cliente) {

		cliente.setAtivo(true);
			notificador.notificar(cliente);
	}
	
}
