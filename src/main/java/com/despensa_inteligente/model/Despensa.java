package com.despensa_inteligente.model;

import com.despensa_inteligente.serializers.ClienteSerializer;
import com.despensa_inteligente.serializers.ProdutoDespensaSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;


@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
//@JsonIgnoreProperties(ignoreUnknown = true)
public class Despensa extends AbstractModel {

	private String nome;
	private String localizacao;

	@OneToMany(mappedBy = "despensa")
	@JsonSerialize(using = ProdutoDespensaSerializer.class)
	private List<ProdutoDespensa> produtosDespensas;
	
	@ManyToOne
    @JsonSerialize(using = ClienteSerializer.class)
	private Cliente cliente;
	
    //Constructor GET
    public Despensa(Long id, String nome, String localizacao, Long idCliente){
        super(id);
        Cliente cliente = new Cliente(idCliente);
        this.nome = nome;
        this.localizacao = localizacao;
        this.cliente = cliente;
    }

    //Constructor POST
    public Despensa(String nome, String localizacao, Long idCliente){
        super();
        Cliente cliente = new Cliente(idCliente);
        this.nome = nome;
        this.localizacao = localizacao;
        this.cliente = cliente;
    }


    public Despensa(Long id) {
	    super(id);
    }

}
