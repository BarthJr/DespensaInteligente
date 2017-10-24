package com.despensa_inteligente.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Categoria extends AbstractModel {
	@NotEmpty(message = "O campo nome da categoria é obrigatório")
	private String nome;
	
}
