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

import com.despensa_inteligente.model.ProdutoDespensa;
import com.despensa_inteligente.repository.ProdutoDespensaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/produtosDespensas")
public class ProdutoDespensaController implements Serializable {
	
	@Autowired private ProdutoDespensaRepository repository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<ProdutoDespensa> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		ProdutoDespensa produtoDespensa = repository.findOne(id);
		return new ResponseEntity<ProdutoDespensa>(produtoDespensa,HttpStatus.OK);
	}
    
//    @GetMapping
//    public ResponseEntity<List<?>> buscaProdutoDespensaPorNome(@RequestParam("nome") Optional<String> nome) {
//    	List<?> produtoDespensa = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//    	if(produtoDespensa == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//        return new ResponseEntity<List<?>>(produtoDespensa,HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<?> buscaProdutoDespensa() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProdutoDespensa produtoDespensa) {
    	repository.save(produtoDespensa);
    	produtoDespensa.setId(null);
    	repository.save(produtoDespensa);
    	if(true)
    		throw new RuntimeErrorException(null, "Test");
    	repository.save(produtoDespensa);
    	return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProdutoDespensa produtoDespensa) {
    	verifyIfProdutoExists(produtoDespensa.getId());
    	return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.OK);
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
