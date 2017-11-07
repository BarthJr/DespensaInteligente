	package com.despensa_inteligente.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.despensa_inteligente.serializers.CategoriaSerializer;
import com.despensa_inteligente.serializers.ClienteSerializer;
import com.despensa_inteligente.serializers.ReceitaSerializer;
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
public class Favorita extends AbstractModel {

	@ManyToOne
//	@JsonIgnoreProperties("receitasFavoritas")
	@JsonSerialize(using = ClienteSerializer.class)
	private Cliente cliente;
	
	@ManyToOne
//	@JsonIgnoreProperties("favoritadasPelosClientes")
	@JsonSerialize(using = ReceitaSerializer.class)
	private Receita receita;

}
