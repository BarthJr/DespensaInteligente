package com.despensa_inteligente.controller;

import com.despensa_inteligente.model.Medida;
import com.despensa_inteligente.repository.MedidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.Serializable;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/medida")
public class MedidaController implements Serializable{

    @Autowired private MedidaRepository repository;

    @GetMapping
    public ModelAndView findAll() {

        ModelAndView mv = new ModelAndView("/medida");
        mv.addObject("medidas", repository.findAll());

        return mv;
    }

    @GetMapping("/add")
    public ModelAndView add(Medida medida) {

        ModelAndView mv = new ModelAndView("/medidaAdd");
        mv.addObject("medida", medida);

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
    public ModelAndView save(@Valid Medida medida, BindingResult result) {

        if(result.hasErrors()) {
            return add(medida);
        }

        repository.save(medida);

        return findAll();
    }
}