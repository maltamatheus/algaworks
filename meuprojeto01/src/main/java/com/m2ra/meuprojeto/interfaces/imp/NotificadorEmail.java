package com.m2ra.meuprojeto.interfaces.imp;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.anotacoes.TipoDoNotificador;
import com.m2ra.meuprojeto.enums.NivelUrgencia;
import com.m2ra.meuprojeto.interfaces.Notificador;
import com.m2ra.meuprojeto.modelo.Cliente;

@Profile("prd")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador{
	
	String msg = "Acompanhe seus pedidos pelo seu e-mail";
	
	public NotificadorEmail() {
		System.out.println("Usando NotificadorEmail");
	}
	
	@Override
	public void notificar(Cliente cliente) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n",
		          cliente.getNome(), cliente.getEmail(), msg);
	}
}
