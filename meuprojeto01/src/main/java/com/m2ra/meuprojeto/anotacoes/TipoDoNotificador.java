package com.m2ra.meuprojeto.anotacoes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;

import com.m2ra.meuprojeto.enums.NivelUrgencia;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface TipoDoNotificador {
	
	NivelUrgencia value();

}
