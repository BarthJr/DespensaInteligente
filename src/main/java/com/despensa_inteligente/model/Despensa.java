package com.despensa_inteligente.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Despensa extends AbstractModel {

	private String nome;
	private String localizacao;
	
	@ManyToMany(mappedBy="despensas", cascade = {CascadeType.MERGE})
//	@JoinTable(name = "ProdutoDespensa",   
//	joinColumns = @JoinColumn(name = "despensa_id", referencedColumnName = "id"),   
//	inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id")) 
	@JsonIgnoreProperties("despensas")
	private List<Produto> produtos  = new ArrayList<>();; 
//	private Set<Produto> produtos = new HashSet<>();
//	private Set<Produto> produtos;
	
}
