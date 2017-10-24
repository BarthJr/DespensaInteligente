	package com.despensa_inteligente.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class ProdutoReceita extends AbstractModel {
	
	@ManyToOne
//	@JoinColumn(nullable = false)
	@JsonIgnoreProperties("produtosReceitas")
	private Produto produto;
	
	@ManyToOne
//	@JoinColumn(nullable = false)
	@JsonIgnoreProperties("produtosReceitas")
	private Receita receita;
	
	private Double quantidade;
	
	@ManyToOne
	@JsonIgnore
//	@JoinColumn(nullable = false)
	private Medida medida;
	
	
	
	
}
