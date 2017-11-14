package com.despensa_inteligente.endpoints;

import java.io.Serializable;

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
import org.springframework.web.bind.annotation.RestController;

import com.despensa_inteligente.model.ProdutoDespensa;
import com.despensa_inteligente.repository.ProdutoDespensaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/produtosDespensas")
public class ProdutoDespensaEndpoint implements Serializable {
	
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
    public ResponseEntity<?> save(@RequestBody ProdutoDespensa produtoDespensa) {
    	return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ProdutoDespensa produtoDespensa) {
    	verifyIfProdutoExists(produtoDespensa.getId());
    	return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.OK);
    }

//    @PutMapping("/fazer-receita")
//    public ResponseEntity<?> update(@RequestBody ProdutoDespensa produtoDespensa) {
//        verifyIfProdutoExists(produtoDespensa.getId());
//        return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.OK);
//    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("ProdutoDespensa não encontrado para o ID "+id);
    }
	
}
