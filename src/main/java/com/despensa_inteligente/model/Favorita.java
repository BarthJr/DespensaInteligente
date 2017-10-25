	package com.despensa_inteligente.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Favorita extends AbstractModel {
	
	
	@ManyToOne
	@JsonIgnoreProperties("receitasFavoritas")
	private Cliente cliente;
	
	@ManyToOne
	@JsonIgnoreProperties("favoritadasPelosClientes")
	private Receita receita;

}
