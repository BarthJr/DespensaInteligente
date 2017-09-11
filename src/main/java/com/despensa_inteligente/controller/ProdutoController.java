package com.despensa_inteligente.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.despensa_inteligente.model.Produto;
import com.despensa_inteligente.repository.ProdutoRepository;

@SuppressWarnings("serial")
@RestController
//@RequestMapping("/produtos")
public class ProdutoController implements Serializable{
	
	@Autowired private ProdutoRepository repository;
	     
    @GetMapping("/produto")
    public ModelAndView findAll() {
         
        ModelAndView mv = new ModelAndView("/produto");
        mv.addObject("produtos", repository.findAll());
         
        return mv;
    }
     
    @GetMapping("/add")
    public ModelAndView add(Produto produto) {
         
        ModelAndView mv = new ModelAndView("/produtoAdd");
        mv.addObject("produto", produto);
         
        return mv;
    }
     
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id) {
         
        return add(repository.findOne(id));
    }
     
    @GetMapping("/delete/{id}")
    public ModelAndView deletar(@PathVariable("id") Long id) {
         
        repository.delete(id);
         
        return findAll();
    }
 
    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
         
        if(result.hasErrors()) {
            return add(produto);
        }
         
        repository.save(produto);
         
        return findAll();
    }
    
// REST SERVICE    
    
    @GetMapping("/produtos/{id}")
	public ResponseEntity<Produto> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		Produto produto = repository.findOne(id);
		return new ResponseEntity<Produto>(produto,HttpStatus.OK);
	}
    
    @GetMapping(value="/produtos")
    public ResponseEntity<List<Produto>> buscaProdutoPorNome(@RequestParam("nome") Optional<String> nome) {
    	List<Produto> produto = (nome.isPresent()) ? (List<Produto>) repository.findByNomeIgnoreCaseContaining(nome) : (List<Produto>) repository.findAll();
//        return new ResponseEntity<>(repository.findByNome(nome), HttpStatus.OK);
    	if(produto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
        return new ResponseEntity<List<Produto>>(produto,HttpStatus.OK);
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
