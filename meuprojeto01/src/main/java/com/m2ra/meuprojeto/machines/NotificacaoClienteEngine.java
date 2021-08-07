package com.m2ra.meuprojeto.machines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.anotacoes.TipoDoNotificador;
import com.m2ra.meuprojeto.enums.NivelUrgencia;
import com.m2ra.meuprojeto.events.ClienteAtivadoEvent;
import com.m2ra.meuprojeto.interfaces.Notificador;

@Component
public class NotificacaoClienteEngine {
	
	@Autowired
	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
	private Notificador notificador;
	
	@EventListener
	public void clienteAtivadoListener(ClienteAtivadoEvent event) {
		notificador.notificar(event.getCliente());
	}
}
