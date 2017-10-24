package com.despensa_inteligente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = 	false)
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cliente extends AbstractModel {

	private String nome;
	private String login;
	private String senha;
	
	@ManyToMany
	@JoinTable(name = "Favorita",   
	joinColumns = @JoinColumn(name = "cliente_id", referencedColumnName = "id"),   
	inverseJoinColumns = @JoinColumn(name = "receita_id", referencedColumnName = "id"))
//	@JsonIgnoreProperties("FavoritadasPelosClientes")
//	@JsonIgnore
	private List<Receita> receitasFavoritas;
}
