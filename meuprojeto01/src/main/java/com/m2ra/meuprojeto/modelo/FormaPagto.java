package com.m2ra.meuprojeto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "tab_forma_pagto")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FormaPagto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	@Column(name = "id_forma_pagto")
	private Long id;
	
	@Column(name = "nome_forma_pagto",nullable = false)
	private String nome;

}
