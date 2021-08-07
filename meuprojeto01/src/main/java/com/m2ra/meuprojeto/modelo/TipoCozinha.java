package com.m2ra.meuprojeto.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonRootName;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tab_tipo_cozinha")
@JsonRootName(value = "tipocozinha")
public class TipoCozinha {
	
	@Id
	@EqualsAndHashCode.Include
	@Column(name = "id_tipo_cozinha")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonProperty(value = "nome_tipo_cozinha")
	@Column(name = "nome_tipo_cozinha")
	private String nome;

}
