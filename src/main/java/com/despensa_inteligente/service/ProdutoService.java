package com.despensa_inteligente.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.despensa_inteligente.model.Produto;
import com.despensa_inteligente.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired private ProdutoRepository repository;
	
	public List<Produto> findAll() {
        return (List<Produto>) repository.findAll();
    }
     
    public Produto findOne(Long id) {
        return repository.findOne(id);
    }
     
    public Produto save(Produto post) {
        return repository.save(post);
    }
     
    public void delete(Long id) {
    	repository.delete(id);
    }

}
