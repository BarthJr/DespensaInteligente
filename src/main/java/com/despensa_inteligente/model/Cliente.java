package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = 	false)
public class Cliente extends AbstractModel {

	private String nome;
	private String login;
	public String senha;
}
