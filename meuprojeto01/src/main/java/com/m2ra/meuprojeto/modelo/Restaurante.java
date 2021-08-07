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
@Table(name = "tab_restaurante")
public class Restaurante {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_resturante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome_restaurante")
	private String nome;
	
	@Column(name = "vl_taxa_frete",nullable = false)
	private BigDecimal taxaFrete;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_cozinha")
	private TipoCozinha tipoCozinha;

}
