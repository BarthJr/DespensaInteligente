	package com.despensa_inteligente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.despensa_inteligente.serializers.ProdutoSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
@JsonSerialize(using = ProdutoSerializer.class)
public class Produto extends AbstractModel {

	private String nome;
	private String marca;
	private String tipo;
	private Double peso;
	
	
	public Produto(String nome, String marca, String tipo, Double peso) {
		super();
		this.nome = nome;
		this.marca = marca;
		this.tipo = tipo;
		this.peso = peso;
	}

	@ManyToOne
	private Cliente cliente;
	
	@ManyToOne
	private Categoria categoria;
	
	@OneToMany(mappedBy = "produto")
	private List<ProdutoDespensa> produtosDespensas;
	
	@OneToMany(mappedBy = "produto")
	private List<ProdutoReceita> produtosReceitas;
	
}
