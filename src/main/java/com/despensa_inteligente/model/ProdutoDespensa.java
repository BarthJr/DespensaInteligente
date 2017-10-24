	package com.despensa_inteligente.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
//	@JsonIgnore
//	@JsonManagedReference
	@JsonIgnoreProperties("produtosDespensas")
	private Produto produto;
	
	@ManyToOne
//	@JsonIgnore
//	@JsonManagedReference
	@JsonIgnoreProperties("produtosDespensas")
	private Despensa despensa;

//	private LocalDateTime validade;
	private Double quantidade;
	
	
	
	
}
