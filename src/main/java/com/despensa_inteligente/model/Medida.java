package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Medida extends AbstractModel {

	private String nome;
	private Double valor;

	//Constructor GET
	public Medida(Long id, String nome, Double valor) {
		super(id);
		this.nome = nome;
		this.valor = valor;

	}

    //Constructor POST
	public Medida(String nome, Double valor) {
		super();
		this.nome = nome;
		this.valor = valor;

	}

	public Medida(Long id){
		super(id);
	}
	
}
