	package com.despensa_inteligente.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.despensa_inteligente.serializers.ProdutoSerializer;
import com.despensa_inteligente.serializers.ReceitaSerializer;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.org.apache.regexp.internal.RE;
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
@ToString
public class ProdutoReceita extends AbstractModel {

	@ManyToOne
//	@JsonIgnoreProperties("produtosReceitas")
	@JsonSerialize(using = ProdutoSerializer.class)
	private Produto produto;

	@ManyToOne
//	@JsonIgnoreProperties("produtosReceitas")
	@JsonSerialize(using = ReceitaSerializer.class)
	private Receita receita;

	private Double quantidade;
	
	@ManyToOne
	@JsonIgnore
	private Medida medida;

	//Constructor GET
	public ProdutoReceita(Long id, Long idProduto, Long idReceita, Double quantidade){
		super(id);
		Produto produto = new Produto(idProduto);
		Receita receita = new Receita(idReceita);
		this.produto = produto;
		this.receita = receita;
		this.quantidade = quantidade;
	}

	//Constructor POST
	public ProdutoReceita(Long idProduto, Long idReceita, Double quantidade){
		super();
		Produto produto = new Produto(idProduto);
		Receita receita = new Receita(idReceita);
		this.produto = produto;
		this.receita = receita;
		this.quantidade = quantidade;
	}


	public ProdutoReceita(Long id){
		super(id);
	}
	
	
	
	
}
