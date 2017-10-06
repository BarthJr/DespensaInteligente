package com.despensa_inteligente.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Medida extends AbstractModel {

	private String nome;
	private Double valor;
	
}
