package com.despensa_inteligente.controller;

import com.despensa_inteligente.model.Produto;
import com.despensa_inteligente.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;

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
}
