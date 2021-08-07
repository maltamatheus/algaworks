package com.m2ra.meuprojeto.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.m2ra.meuprojeto.modelo.Cliente;

public class AtivacaoClienteEvent {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;
	
	public void ativarCliente(Cliente cliente) {
		cliente.setAtivo(true);
		eventPublisher.publishEvent(new ClienteAtivadoEvent());
	}

}
