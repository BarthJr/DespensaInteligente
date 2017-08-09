package com.despensa_inteligente.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.despensa_inteligente.model.Produto;
import com.despensa_inteligente.service.ProdutoService;

@SuppressWarnings("serial")
@RestController
public class ProdutoController implements Serializable{
	
	@Autowired private ProdutoService service;
	     
    @GetMapping("/")
    public ModelAndView findAll() {
         
        ModelAndView mv = new ModelAndView("/produto");
        mv.addObject("produtos", service.findAll());
         
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
         
        return add(service.findOne(id));
    }
     
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") Long id) {
         
        service.delete(id);
         
        return findAll();
    }
 
    @PostMapping("/save")
    public ModelAndView save(@Valid Produto produto, BindingResult result) {
         
        if(result.hasErrors()) {
            return add(produto);
        }
         
        service.save(produto);
         
        return findAll();
    }
    
    @RequestMapping(value = "/produtos", method = RequestMethod.GET)
	public ResponseEntity<List<Produto>> listar() {
		List<Produto> produto = service.findAll();
		if(produto == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Produto>>(produto,HttpStatus.OK);
	}
	
}
