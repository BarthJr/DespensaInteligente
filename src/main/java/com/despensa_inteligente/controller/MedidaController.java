package com.despensa_inteligente.controller;

import java.io.Serializable;

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
import org.springframework.web.bind.annotation.RestController;

import com.despensa_inteligente.model.Medida;
import com.despensa_inteligente.repository.MedidaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/medidas")
public class MedidaController implements Serializable {
	
	@Autowired private MedidaRepository repository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<Medida> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Medida medida = repository.findOne(id);
		return new ResponseEntity<Medida>(medida,HttpStatus.OK);
	}
    
//    @GetMapping
//    public ResponseEntity<List<?>> buscaMedidaPorNome(@RequestParam("nome") Optional<String> nome) {
//    	List<?> medida = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//    	if(medida == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//        return new ResponseEntity<List<?>>(medida,HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<?> buscaMedida(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Medida medida) {
    	return new ResponseEntity<>(repository.save(medida), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Medida medida) {
    	verifyIfProdutoExists(medida.getId());
    	return new ResponseEntity<>(repository.save(medida), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("Medida não encontrada para o ID "+id);
    }
	
}
