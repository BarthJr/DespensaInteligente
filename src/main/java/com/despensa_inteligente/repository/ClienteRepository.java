package com.despensa_inteligente.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.despensa_inteligente.model.Cliente;

@RepositoryRestResource
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
	List<Cliente> findByNomeIgnoreCaseContaining(Optional<String> nome);

}
