package com.despensa_inteligente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.Categoria;

@RepositoryRestResource
public interface CategoriaRepository extends PagingAndSortingRepository<Categoria, Long>{
	List<Categoria> findByNomeIgnoreCaseContaining(Optional<String> nome);
}
