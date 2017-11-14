package com.despensa_inteligente.endpoint;

import com.despensa_inteligente.model.Produto;
import com.despensa_inteligente.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("serial")
@RestController
//@RequestMapping("/produtos")
public class ProdutoEndpoint implements Serializable{
	
	@Autowired private ProdutoRepository repository;
	     
    @GetMapping("/produtos/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Produto produto = repository.findOne(id);
		return new ResponseEntity<>(produto,HttpStatus.OK);
	}
    
    @GetMapping(value="/produtos")
    public ResponseEntity<List<?>> buscaProdutoPorNome(@RequestParam("nome") Optional<String> nome) {
    	List<?> produto = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//        return new ResponseEntity<>(repository.findByNome(nome), HttpStatus.OK);
    	if(produto == null) {
    		
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<List<?>>(produto,HttpStatus.OK);
    }
    
    @PostMapping(value="/produtos")
    public ResponseEntity<?> save(@RequestBody Produto produto) {
    	return new ResponseEntity<>(repository.save(produto), HttpStatus.CREATED);
    }
    
    @PutMapping(value="/produtos")
    public ResponseEntity<?> update(@RequestBody Produto produto) {
    	verifyIfProdutoExists(produto.getId());
    	return new ResponseEntity<>(repository.save(produto), HttpStatus.OK);
    }
    @DeleteMapping(value = "/produtos/{id}")
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
