	package com.despensa_inteligente.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.despensa_inteligente.serializers.*;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Produto extends AbstractModel {

	private String nome;
	private String marca;
	private String tipo;
    private Double peso;

	@ManyToOne
    @JsonIgnore
	private Cliente cliente;

	@ManyToOne
    @JsonSerialize(using = CategoriaSerializer.class)
	private Categoria categoria;

	@OneToMany(mappedBy = "produto")
    @JsonIgnore
	private List<ProdutoDespensa> produtosDespensas;

	@OneToMany(mappedBy = "produto")
    @JsonIgnore
	private List<ProdutoReceita> produtosReceitas;

	//Constructor GET
    public Produto(Long id, String nome, String marca, String tipo,
                   Double peso, Long idCategoria) {
        super(id);
        Categoria categoria = new Categoria(idCategoria);

        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
        this.peso = peso;
        this.categoria = categoria;
    }

    // Constructor POST
    public Produto(String nome, String marca, String tipo,
                   Double peso, Long idCategoria) {
        super();
        Categoria categoria = new Categoria(idCategoria);

        this.nome = nome;
        this.marca = marca;
        this.tipo = tipo;
        this.peso = peso;
        this.categoria = categoria;
    }


    public Produto(Long id){
        super(id);
    }

}
