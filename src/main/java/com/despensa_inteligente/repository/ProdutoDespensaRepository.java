package com.despensa_inteligente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.ProdutoDespensa;

@RepositoryRestResource
public interface ProdutoDespensaRepository extends CrudRepository<ProdutoDespensa, Long>{

}
