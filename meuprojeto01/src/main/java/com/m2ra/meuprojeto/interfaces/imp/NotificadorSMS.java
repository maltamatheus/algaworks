package com.m2ra.meuprojeto.interfaces.imp;

import org.springframework.stereotype.Component;

import com.m2ra.meuprojeto.anotacoes.TipoDoNotificador;
import com.m2ra.meuprojeto.enums.NivelUrgencia;
import com.m2ra.meuprojeto.interfaces.Notificador;
import com.m2ra.meuprojeto.modelo.Cliente;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador{
	
	String msg = "Acompanhe seus pedidos pelo seu SMS";
	@Override
	public void notificar(Cliente cliente) {
		System.out.printf("Notificando %s através do Telefone %s: %s\n",
		          cliente.getNome(), cliente.getTelefone(), msg);
	}
}
