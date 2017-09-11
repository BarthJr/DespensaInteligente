package com.despensa_inteligente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.Despensa;

@RepositoryRestResource
public interface DespensaRepository extends CrudRepository<Despensa, Long> {
	List<Despensa> findByNomeIgnoreCaseContaining(Optional<String> nome);

}
