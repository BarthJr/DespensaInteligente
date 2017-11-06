package com.despensa_inteligente.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.ProdutoDespensa;

@RepositoryRestResource
public interface ProdutoDespensaRepository extends CrudRepository<ProdutoDespensa, Long>{
	@Query("select sum(pd.quantidade) from ProdutoDespensa pd where despensa.id=:despensa and produto.id=:produto")
	List<ProdutoDespensa> findQuantidade(@Param("despensa") Long idDespensa, @Param("produto") Long idProduto);

}
