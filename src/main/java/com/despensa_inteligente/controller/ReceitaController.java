package com.despensa_inteligente.controller;

import com.despensa_inteligente.model.Receita;
import com.despensa_inteligente.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/receita")
public class ReceitaController implements Serializable{

    @Autowired private ReceitaRepository repository;

    @GetMapping
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView("/receita");
        mv.addObject("receitas", repository.findAll());

        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(Receita receita) {

        ModelAndView mv = new ModelAndView("receitaAdd");
        mv.addObject("receita", receita);

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
    public ModelAndView save(@Valid Receita receita, BindingResult result) {

        if(result.hasErrors()) {
            return add(receita);
        }

        repository.save(receita);

        return findAll();
    }
}