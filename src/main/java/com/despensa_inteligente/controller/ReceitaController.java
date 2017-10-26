package com.despensa_inteligente.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.despensa_inteligente.model.Receita;
import com.despensa_inteligente.repository.ReceitaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/receitas")
public class ReceitaController implements Serializable {
	
	@Autowired private ReceitaRepository repository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<Receita> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Receita receita = repository.findOne(id);
		return new ResponseEntity<Receita>(receita,HttpStatus.OK);
	}
    
//    @GetMapping
//    public ResponseEntity<List<?>> buscaReceitaPorNome(@RequestParam("nome") Optional<String> nome) {
//    	List<?> receita = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//    	if(receita == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//        return new ResponseEntity<List<?>>(receita,HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<?> buscaReceita(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Receita receita) {
    	repository.save(receita);
    	receita.setId(null);
    	repository.save(receita);
    	if(true)
    		throw new RuntimeErrorException(null, "Test");
    	repository.save(receita);
    	return new ResponseEntity<>(repository.save(receita), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Receita receita) {
    	verifyIfProdutoExists(receita.getId());
    	return new ResponseEntity<>(repository.save(receita), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("Receita não encontrada para o ID "+id);
    }
	
}
