package com.despensa_inteligente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.Produto;

@RepositoryRestResource
public interface ProdutoRepository extends CrudRepository<Produto, Long>{
	List<Produto> findByNomeIgnoreCaseContaining(Optional<String> nome);

}
