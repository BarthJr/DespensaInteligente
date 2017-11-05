package com.despensa_inteligente.model;

import com.despensa_inteligente.serializers.ClienteSerializer2;
import com.despensa_inteligente.serializers.ProdutoDespensaSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

//import com.despensa_inteligente.serializers.ClienteDeserializer;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
//@JsonSerialize(using = DespensaSerializer.class)
//@JsonIgnoreProperties(ignoreUnknown = true)
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class Despensa extends AbstractModel {

	private String nome;
	private String localizacao;

	@OneToMany(mappedBy = "despensa")
	@JsonSerialize(using = ProdutoDespensaSerializer.class)
	private List<ProdutoDespensa> produtosDespensas;
	
	@ManyToOne
//	@JsonSerialize(using = ClienteSerializer2.class)
//    @JsonDeserialize(using = ClienteDeserializer.class)
//    @JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
//    @JsonIdentityReference(alwaysAsId=true)
	private Cliente cliente;
	
	public Despensa(Long id, String nome, String localizacao) {
		super(id);
		this.nome = nome;
		this.localizacao = localizacao;
	}

    public Despensa(String nome, String localizacao) {
        super();
        this.nome = nome;
        this.localizacao = localizacao;
    }

//	@JsonCreator
//    public Despensa(@JsonProperty("nome")String nome, @JsonProperty("localizacao")String localizacao, @JsonProperty("cliente")Long idCliente) {
//        super();
//        this.nome = nome;
//        this.localizacao = localizacao;
//        Cliente cliente = new Cliente(idCliente);
//        this.cliente = cliente;
//    }

    public Despensa(Long id, String nome, String localizacao, Cliente cliente, List<ProdutoDespensa> produtosDespensas){
        super(id);
        this.nome = nome;
        this.localizacao = localizacao;
        this.cliente = cliente;
        this.produtosDespensas = produtosDespensas;
    }

    public Despensa(Long id, String nome, String localizacao, Cliente cliente){
        super(id);
        this.nome = nome;
        this.localizacao = localizacao;
        this.cliente = cliente;
    }

    public Despensa(String nome, String localizacao, Cliente cliente) {
        super();
        this.nome = nome;
        this.localizacao = localizacao;
        this.cliente = cliente;
    }

    public Despensa(Cliente cliente) {
        super();
        this.cliente = cliente;
    }






    public Despensa(Long id) {
	    super(id);
    }





}
