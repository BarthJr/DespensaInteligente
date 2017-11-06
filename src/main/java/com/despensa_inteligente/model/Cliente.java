package com.despensa_inteligente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
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
public class Cliente extends AbstractModel {

	private String nome;
	private String login;
	private String senha;
	
	@OneToMany(mappedBy = "cliente")
	private List<Favorita> receitasFavoritas;


	//Constructor GET
	public Cliente(Long id, String nome, String login, String senha){
		super(id);
		this.nome = nome;
		this.login = login;
		this.senha = senha;

	}

	//Constructor POST
	public Cliente(String nome, String login, String senha){
		super();
		this.nome = nome;
		this.login = login;
		this.senha = senha;

	}

	public Cliente(Long id){
		super(id);
	}
}
