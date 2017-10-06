package com.despensa_inteligente.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.Medida;



@RepositoryRestResource
public interface MedidaRepository extends CrudRepository<Medida, Long>{

}
