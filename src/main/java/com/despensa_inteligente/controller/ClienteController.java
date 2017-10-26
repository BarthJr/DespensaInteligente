package com.despensa_inteligente.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.despensa_inteligente.model.Cliente;
import com.despensa_inteligente.repository.ClienteRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/clientes")
public class ClienteController implements Serializable {
	
	@Autowired private ClienteRepository repository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Cliente cliente = repository.findOne(id);
		return new ResponseEntity<Cliente>(cliente,HttpStatus.OK);
	}
    
    @GetMapping
    public ResponseEntity<List<Cliente>> buscaClientePorNome(@RequestParam("nome") Optional<String> nome) {
    	List<Cliente> cliente = (nome.isPresent()) ? (List<Cliente>) repository.findByNomeIgnoreCaseContaining(nome) : (List<Cliente>) repository.findAll();
//        return new ResponseEntity<>(repository.findByNome(nome), HttpStatus.OK);
    	if(cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<List<Cliente>>(cliente,HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Cliente cliente) {
    	return new ResponseEntity<>(repository.save(cliente), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Cliente cliente) {
    	verifyIfProdutoExists(cliente.getId());
    	return new ResponseEntity<>(repository.save(cliente), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("Cliente não encontrado para o ID "+id);
    }
	
}
