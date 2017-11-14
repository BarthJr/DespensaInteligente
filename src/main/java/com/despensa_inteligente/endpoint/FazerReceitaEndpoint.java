package com.despensa_inteligente.endpoints;

import java.io.Serializable;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.despensa_inteligente.model.ProdutoDespensa;
import com.despensa_inteligente.repository.DespensaRepository;
import com.despensa_inteligente.repository.ProdutoDespensaRepository;
import com.despensa_inteligente.repository.ProdutoRepository;

@SuppressWarnings("serial")
@RestController
@RequestMapping("/fazer-receita")
public class FazerReceitaEndpoint implements Serializable {
	
	@Autowired private ProdutoDespensaRepository repository;
	@Autowired private DespensaRepository despensaRepository;
	@Autowired private ProdutoRepository produtoRepository;
	     
    @GetMapping("/{id}")
	public ResponseEntity<ProdutoDespensa> buscar(@PathVariable("id") Long id) {
    	verifyIfProdutoExists(id);
		ProdutoDespensa produtoDespensa = repository.findOne(id);
		return new ResponseEntity<ProdutoDespensa>(produtoDespensa,HttpStatus.OK);
	}
    
//    @GetMapping
//    public ResponseEntity<List<?>> buscaDespensaPorNome(@RequestParam("nome") Optional<String> nome) {
//    	List<?> produtoDespensa = (nome.isPresent()) ? (List<?>) repository.findByNomeIgnoreCaseContaining(nome) : (List<?>) repository.findAll();
//    	if(produtoDespensa == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//        return new ResponseEntity<List<?>>(produtoDespensa,HttpStatus.OK);
//    }
    
    @GetMapping
    public ResponseEntity<?> buscaQuantidadeProduto(@RequestParam(value = "despensa", required=false) Long idDespensa, @RequestParam(value = "produto", required=false) Long idProduto) {
//    	verifyIfProdutoExistsWithMsg(idDespensa, "ProdutoDespensa não encontrada para o ID ");
//    	verifyIfProdutoExistsWithMsg(idProduto, "Produto não encontrado para o ID ");
//    	String hql = "select produto_despensa where despensa_id='%s%' and produto_id='%s%'";
    	return new ResponseEntity<>(repository.findQuantidade(idDespensa, idProduto), HttpStatus.OK);
//        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProdutoDespensa produtoDespensa) {
    	repository.save(produtoDespensa);
    	produtoDespensa.setId(null);
    	repository.save(produtoDespensa);
    	if(true)
    		throw new RuntimeErrorException(null, "Test");
    	repository.save(produtoDespensa);
    	return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.CREATED);
    }
    
//    @PutMapping
//    public ResponseEntity<?> update(@RequestBody ProdutoDespensa produtoDespensa) {
//    	verifyIfProdutoExists(produtoDespensa.getId());
//    	return new ResponseEntity<>(repository.save(produtoDespensa), HttpStatus.OK);
//    }

    @PutMapping
    public ResponseEntity<?> buscaQuantidadeProduto(@RequestParam(value = "despensa", required=false) Long idDespensa,
                                                    @RequestParam(value = "produto", required=false) Long idProduto,
                                                    @RequestParam(value = "quantidade", required=false) Double quantidade) {
        Double quantidadeDespensas = repository.findQuantidade(idDespensa, idProduto);
        Double quantidadeDescontada;
        if(quantidade <= quantidadeDespensas)
            quantidadeDescontada = quantidadeDespensas - quantidade;
        else
            quantidadeDescontada = quantidadeDespensas;
        repository.diminuiQuantidade(idDespensa, idProduto, quantidadeDescontada);
        return new ResponseEntity<>(repository.findQuantidade(idDespensa, idProduto), HttpStatus.OK);

    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long  id) {
    	verifyIfProdutoExists(id);
    	  repository.delete(id);
    	  return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    private void verifyIfProdutoExistsWithMsg(Long id, String msg) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException(msg+id);
    }
    
    private void verifyIfProdutoExists(Long id) {
    	if(repository.findOne(id) == null)
    		throw new ResourceNotFoundException("ProdutoDespensa não encontrada para o ID "+id);
    }
	
}
