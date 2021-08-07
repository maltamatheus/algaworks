package com.m2ra.meuprojeto.interfaces.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.anotacoes.TipoDoNotificador;
import com.m2ra.meuprojeto.configurators.ConfiguracoesNotificador;
import com.m2ra.meuprojeto.enums.NivelUrgencia;
import com.m2ra.meuprojeto.interfaces.Notificador;
import com.m2ra.meuprojeto.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador{
	
	@Autowired
	private ConfiguracoesNotificador notificadorConfig;
	
	String msg = "Acompanhe seus pedidos pelo seu e-mail";
	
	public NotificadorEmail(ConfiguracoesNotificador notificadorConfig) {
		System.out.println("Iniciando NotificadorEmail");
		this.notificadorConfig = notificadorConfig;
		System.out.println("Host: " + notificadorConfig.getHostServidor());
		System.out.println("Porta: " + notificadorConfig.getPortaServidor());
	}

	@Override
	public void notificar(Cliente cliente) {
		
		System.out.println("Host: " + notificadorConfig.getHostServidor());
		
		System.out.println("Porta: " + notificadorConfig.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n",
		          cliente.getNome(), cliente.getEmail(), msg);
	}
}
