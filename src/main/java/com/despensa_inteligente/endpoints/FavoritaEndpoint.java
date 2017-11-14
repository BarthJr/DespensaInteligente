package com.despensa_inteligente.endpoints;

import java.io.Serializable;

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

import com.despensa_inteligente.model.Favorita;
import com.despensa_inteligente.repository.FavoritaRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/favoritas")
public class FavoritaEndpoint implements Serializable {
	
	@Autowired private FavoritaRepository repository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<Favorita> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Favorita favorita = repository.findOne(id);
		return new ResponseEntity<Favorita>(favorita,HttpStatus.OK);
	}
    
//    @GetMapping
//    public ResponseEntity<List<?>> buscaFavoritaPorNome(@RequestParam("nome") Optional<String> nome) {
//    	List<?> favorita = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//    	if(favorita == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//        return new ResponseEntity<List<?>>(favorita,HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<?> buscaFavorita(Pageable pageable) {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Favorita favorita) {
    	return new ResponseEntity<>(repository.save(favorita), HttpStatus.CREATED);
    }
    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody Favorita favorita) {
    	verifyIfProdutoExists(favorita.getId());
    	return new ResponseEntity<>(repository.save(favorita), HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("Receita favorita não encontrada para o ID "+id);
    }
	
}
