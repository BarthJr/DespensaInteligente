	package com.despensa_inteligente.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.despensa_inteligente.serializers.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProdutoDespensa extends AbstractModel {
	
	
	
	public ProdutoDespensa(Double quantidade) {
		super();
		this.quantidade = quantidade;
	}
	@ManyToOne
	@JsonIgnoreProperties("produtosDespensas")
	private Produto produto;
	
	@ManyToOne
	@JsonIgnoreProperties("produtosDespensas")
	private Despensa despensa;

	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate validade;
	private Double quantidade;
	
	
	
	
}
