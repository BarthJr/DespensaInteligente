	package com.despensa_inteligente.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Produto extends AbstractModel {

	private String nome;
	private String marca;
	private String tipo;
	private Double peso;
	
//	@ManyToMany(cascade={CascadeType.MERGE, CascadeType.PERSIST})
	@ManyToMany
//	@JoinTable(name="Produto_Despensa",
//			   joinColumns = @JoinColumn(name = "produto_id"),
//			   inverseJoinColumns = @JoinColumn(name = "despensa_id"))
	private List<Despensa> despensas =  new ArrayList<>();
	
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Cliente cliente;
	
	@ManyToOne
	private Categoria categoria;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getPeso() {
		return peso;
	}
	public void setPeso(Double peso) {
		this.peso = peso;
	}
	public List<Despensa> getDespensas() {
		return despensas;
	}
	public void setDespensas(List<Despensa> despensas) {
		this.despensas = despensas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	
}
