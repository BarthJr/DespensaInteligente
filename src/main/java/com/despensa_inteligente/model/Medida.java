package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Medida extends AbstractModel {

	private String nome;
	private Double valor;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
	

}
