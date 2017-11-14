package com.despensa_inteligente.controller;

import com.despensa_inteligente.model.Categoria;
import com.despensa_inteligente.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/categoria")
public class CategoriaController implements Serializable{

    @Autowired private CategoriaRepository repository;

    @GetMapping
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView("/categoria");
        mv.addObject("categorias", repository.findAll());

        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(Categoria categoria) {

        ModelAndView mv = new ModelAndView("/categoriaAdd");
        mv.addObject("categoria", categoria);

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
    public ModelAndView save(@Valid Categoria categoria, BindingResult result) {

        if(result.hasErrors()) {
            return add(categoria);
        }

        repository.save(categoria);

        return findAll();
    }
}