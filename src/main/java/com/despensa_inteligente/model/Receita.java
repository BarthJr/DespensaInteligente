package com.despensa_inteligente.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Receita extends AbstractModel {

	private String titulo;
	private String modoPreparo;
//	private LocalDateTime tempoExecucao;
	private Double quantidade;
	
	@ManyToOne
	private Cliente cliente;
	
//	@OneToMany(mappedBy = "receita", cascade = CascadeType.ALL, orphanRemoval = false,
//			fetch = FetchType.LAZY)
//	@JsonIgnoreProperties("produtos")
	@OneToMany(mappedBy = "receita")
	@JsonIgnoreProperties("receita")
	private List<ProdutoReceita> produtosReceitas = new ArrayList<>();
	
	@ManyToMany(mappedBy = "receitasFavoritas")
//	@JsonIgnoreProperties("receitasFavoritas")
//	@JsonIgnore
	private List<Cliente> FavoritadasPelosClientes;
	
}
