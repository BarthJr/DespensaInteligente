package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Categoria extends AbstractModel {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

}
