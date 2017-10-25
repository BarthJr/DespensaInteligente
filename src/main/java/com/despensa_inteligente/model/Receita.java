package com.despensa_inteligente.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.despensa_inteligente.serializers.ReceitaSerializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
@JsonSerialize(using = ReceitaSerializer.class)
public class Receita extends AbstractModel {

	private String titulo;
	private String modoPreparo;
//	private LocalDateTime tempoExecucao;
	private Double quantidade;
	
	@ManyToOne
	private Cliente cliente;
	
	@OneToMany(mappedBy = "receita")
	@JsonIgnoreProperties("receita")
	private List<ProdutoReceita> produtosReceitas = new ArrayList<>();
	
	@OneToMany(mappedBy = "receita")
	private List<Favorita> favoritadasPelosClientes;
	
}
