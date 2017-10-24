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
import com.despensa_inteligente.model.Despensa;
import com.despensa_inteligente.repository.ClienteRepository;
import com.despensa_inteligente.repository.DespensaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/despensas")
public class DespensaController implements Serializable {
	
	@Autowired private DespensaRepository repository;
	@Autowired private ClienteRepository clienteRepository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<Despensa> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Despensa despensa = repository.findOne(id);
		return new ResponseEntity<Despensa>(despensa,HttpStatus.OK);
	}
    
    @GetMapping
    public ResponseEntity<List<Despensa>> buscaDespensaPorNome(@RequestParam("nome") Optional<String> nome) {
    	List<Despensa> despensa = (nome.isPresent()) ? (List<Despensa>) repository.findByNomeIgnoreCaseContaining(nome) : (List<Despensa>) repository.findAll();
//        return new ResponseEntity<>(repository.findByNome(nome), HttpStatus.OK);
    	if(despensa == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<List<Despensa>>(despensa,HttpStatus.OK);
    }
    
    @GetMapping(value="/cliente/{id}")
	public ResponseEntity<List<Despensa>> buscaDespensaPorCliente(@PathVariable("id") Long id) {
    	Cliente cliente = clienteRepository.findOne(id);
    	List<Despensa> despensa = (List<Despensa>) repository.findByCliente(cliente);
  	if(despensa == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
      return new ResponseEntity<List<Despensa>>(despensa,HttpStatus.OK);
	}
    
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Despensa despensa) {
    	return new ResponseEntity<>(repository.save(despensa), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Despensa despensa) {
    	verifyIfProdutoExists(despensa.getId());
    	return new ResponseEntity<>(repository.save(despensa), HttpStatus.OK);
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
