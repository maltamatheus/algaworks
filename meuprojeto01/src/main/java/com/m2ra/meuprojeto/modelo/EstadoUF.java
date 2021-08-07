package com.m2ra.meuprojeto.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tab_estado_uf")
public class EstadoUF {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_estado_uf")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="sigla_estado_uf")
	private String sigla;
	
	@Column(name = "nome_estado_uf")
	private String nome;
	
}
