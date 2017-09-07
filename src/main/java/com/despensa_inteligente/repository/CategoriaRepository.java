package com.despensa_inteligente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.Categoria;

@RepositoryRestResource
public interface CategoriaRepository extends CrudRepository<Categoria, Long>{

}
