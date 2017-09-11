package com.despensa_inteligente.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Despensa extends AbstractModel {

	private String nome;
	private String localizacao;
	
//	@ManyToMany(mappedBy="despensas", cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@ManyToMany(mappedBy="despensas")
	private List<Produto> produtos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}
	public List<Produto> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	} 
	
	
	
	
	
	

}
