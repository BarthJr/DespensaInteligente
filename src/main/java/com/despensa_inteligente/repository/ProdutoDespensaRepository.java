package com.despensa_inteligente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.ProdutoDespensa;

import javax.transaction.Transactional;

@RepositoryRestResource

public interface ProdutoDespensaRepository extends CrudRepository<ProdutoDespensa, Long>{
	@Query("select sum(pd.quantidade) from ProdutoDespensa pd where pd.despensa.id=:despensa and pd.produto.id=:produto")
	Double findQuantidade(@Param("despensa") Long idDespensa, @Param("produto") Long idProduto);

    @Modifying
    @Transactional
	@Query("update ProdutoDespensa pd set pd.quantidade = :quantidadeDescontada where pd.despensa.id=:despensa and pd.produto.id=:produto")
	void diminuiQuantidade(@Param("despensa") Long idDespensa,
                             @Param("produto") Long idProduto,
                             @Param("quantidadeDescontada") Double quantidadeDescontada
                             );

}
