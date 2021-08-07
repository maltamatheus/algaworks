package com.m2ra.meuprojeto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tab_cidade")
public class Cidade {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_cidade")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@OrderBy(clause = "ASC")
	private Long id;
	
	@Column(name="nome_cidade")
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "id_estado_uf")
	private EstadoUF estadoUF;
	
}
