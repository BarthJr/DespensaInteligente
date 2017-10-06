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

import com.despensa_inteligente.model.Categoria;
import com.despensa_inteligente.repository.CategoriaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/categorias")
public class CategoriaController implements Serializable {
	
	@Autowired private CategoriaRepository repository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Categoria categoria = repository.findOne(id);
		return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
	}
    
//    @GetMapping
//    public ResponseEntity<List<?>> buscaCategoriaPorNome(@RequestParam("nome") Optional<String> nome) {
//    	List<?> categoria = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//    	if(categoria == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//        return new ResponseEntity<List<?>>(categoria,HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<?> buscaCategoria(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(pageable), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Categoria categoria) {
    	repository.save(categoria);
    	categoria.setId(null);
    	repository.save(categoria);
    	if(true)
    		throw new RuntimeErrorException(null, "Test");
    	repository.save(categoria);
    	return new ResponseEntity<>(repository.save(categoria), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Categoria categoria) {
    	verifyIfProdutoExists(categoria.getId());
    	return new ResponseEntity<>(repository.save(categoria), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("Produto não encontrado para o ID "+id);
    }
	
}
