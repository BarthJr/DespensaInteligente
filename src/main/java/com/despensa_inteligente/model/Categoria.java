package com.despensa_inteligente.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Categoria extends AbstractModel {
	@NotEmpty(message = "O campo nome da categoria é obrigatório")
	private String nome;
	
}
