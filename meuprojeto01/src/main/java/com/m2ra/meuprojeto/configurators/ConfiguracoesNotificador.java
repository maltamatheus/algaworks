package com.m2ra.meuprojeto.configurators;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class ConfiguracoesNotificador {
	
	String hostServidor;
	
	Integer portaServidor = 28;
	
	public ConfiguracoesNotificador() {
		System.out.println("Iniciando ConfiguracoesNotificador");
	}

	public String getHostServidor() {
		return hostServidor;
	}

	public void setHostServidor(String hostServidor) {
		this.hostServidor = hostServidor;
	}

	public Integer getPortaServidor() {
		return portaServidor;
	}

	public void setPortaServidor(Integer portaServidor) {
		this.portaServidor = portaServidor;
	}
}
