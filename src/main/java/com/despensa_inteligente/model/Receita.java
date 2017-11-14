package com.despensa_inteligente.model;

import com.despensa_inteligente.serializers.ClienteSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Receita extends AbstractModel {

	private String titulo;
	private String modoPreparo;
//	private LocalDateTime tempoExecucao;
	private Double quantidade;
	
	@ManyToOne
	@JsonSerialize(using = ClienteSerializer.class)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "receita")
	@JsonIgnoreProperties("receita")
	private List<ProdutoReceita> produtosReceitas = new ArrayList<>();
	
	@OneToMany(mappedBy = "receita")
//	@JsonIgnoreProperties("receita")
	@JsonIgnore
	private List<Favorita> favoritadasPelosClientes;


	//Constructor GET
	public Receita(Long id, String titulo, String modoPreparo, Double quantidade,
				   Long idCliente) {
		super(id);
		Cliente cliente = new Cliente(idCliente);

		this.titulo = titulo;
		this.modoPreparo = modoPreparo;
		this.quantidade = quantidade;
		this.cliente = cliente;
	}

	//Constructor POST
	public Receita(String titulo, String modoPreparo, Double quantidade,
				   Long idCliente) {
		super();
		Cliente cliente = new Cliente(idCliente);

		this.titulo = titulo;
		this.modoPreparo = modoPreparo;
		this.quantidade = quantidade;
		this.cliente = cliente;
	}

	public Receita(Long id){
		super(id);
	}
}
