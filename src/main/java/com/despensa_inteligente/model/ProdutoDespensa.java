	package com.despensa_inteligente.model;

	import com.despensa_inteligente.serializers.*;
    import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
    import com.fasterxml.jackson.databind.annotation.JsonSerialize;
	import lombok.*;

    import javax.persistence.Entity;
	import javax.persistence.ManyToOne;
	import java.time.LocalDate;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@ToString
public class ProdutoDespensa extends AbstractModel {

	@ManyToOne
//	@JsonIgnoreProperties("produtosDespensas")
    @JsonSerialize(using = ProdutoSerializer.class)
	private Produto produto;
	
	@ManyToOne
//	@JsonIgnoreProperties("produtosDespensas")
    @JsonSerialize(using = DespensaSerializer.class)
	private Despensa despensa;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate validade;
    private Double quantidade;

    //Constructor GET
    public ProdutoDespensa(Long id, Long idProduto, Long idDespensa, Double quantidade){
        super(id);

        Produto produto = new Produto(idProduto);
        Despensa despensa = new Despensa(idDespensa);

        this.produto = produto;
        this.despensa = despensa;
        this.quantidade = quantidade;
    }

    //Constructor POST
    public ProdutoDespensa(Long idProduto, Long idDespensa, Double quantidade){
        super();

        Produto produto = new Produto(idProduto);
        Despensa despensa = new Despensa(idDespensa);

        this.produto = produto;
        this.despensa = despensa;
        this.quantidade = quantidade;
    }


	public ProdutoDespensa(Long id){
		super(id);
	}


}
