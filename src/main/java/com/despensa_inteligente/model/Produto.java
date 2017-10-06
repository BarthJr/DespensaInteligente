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
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
public class Produto extends AbstractModel {

	private String nome;
	private String marca;
	private String tipo;
	private Double peso;
	
	@ManyToMany(cascade = {CascadeType.MERGE})
	@JsonIgnoreProperties("produtos")  
	private List<Despensa> despensas = new ArrayList<>();
//	private Set<Despensa> despensas =  new HashSet<>();
//	private Set<Despensa> despensas;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	private Categoria categoria;
	
	
	
}
