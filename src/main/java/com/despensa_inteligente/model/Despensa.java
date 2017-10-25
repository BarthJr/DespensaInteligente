package com.despensa_inteligente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Despensa extends AbstractModel {

	private String nome;
	private String localizacao;

	@OneToMany(mappedBy = "despensa")
	@JsonIgnoreProperties("despensa")
	private List<ProdutoDespensa> produtosDespensas;
	
	@ManyToOne
	private Cliente cliente;
	
	public Despensa(String nome, String localizacao) {
		super();
		this.nome = nome;
		this.localizacao = localizacao;
	}
	
}
