package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = 	false)
public class Cliente extends AbstractModel {

	private String nome;
	private String login;
	public String senha;
	
	
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
}
