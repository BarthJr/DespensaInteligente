	package com.despensa_inteligente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
	@JoinColumn(nullable = false)
	@JsonIgnore
	private Cliente cliente;
	
	@ManyToOne
//	@JsonIgnore
	private Categoria categoria;
	
//	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = false,
//			fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "produto")
//	@JsonIgnoreProperties("produto")
	@JsonIgnore
	private List<ProdutoDespensa> produtosDespensas;
	
//	@OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = false,
//			fetch = FetchType.LAZY)
	@OneToMany(mappedBy = "produto")
	@JsonIgnoreProperties("produto")
	@JsonIgnore
	private List<ProdutoReceita> produtosReceitas;
	
}
