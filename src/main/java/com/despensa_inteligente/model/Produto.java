	package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Produto extends AbstractModel {

	private String nome;
	private String marca;
	private String tipo;
	private Double peso;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	
	
}
