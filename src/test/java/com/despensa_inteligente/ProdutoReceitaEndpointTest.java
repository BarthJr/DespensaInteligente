package com.despensa_inteligente;

import com.despensa_inteligente.model.*;
import com.despensa_inteligente.repository.ProdutoReceitaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.*;

public class ProdutoReceitaEndpointTest extends DespensaInteligenteApplicationTests{

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ProdutoReceitaRepository produtoReceitaRepository;

    ProdutoReceita produtoReceita;

    @Before
    public void setUp() throws Exception {
        produtoReceita = new ProdutoReceita(1L, 2L,1L, 5.0);
        BDDMockito.when(produtoReceitaRepository.findOne(produtoReceita.getId())).thenReturn(produtoReceita);
    }

    @Test
    public void procura_produtoReceita_por_id(){
        ResponseEntity<ProdutoReceita> response = restTemplate.getForEntity("/produtosReceitas/{id}", ProdutoReceita.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void lista_produtoReceitas(){
        List<ProdutoReceita> produtoReceitas = asList(produtoReceita,
                new ProdutoReceita(1L, 1L,2L, 15.0)
        );
        BDDMockito.when(produtoReceitaRepository.findAll()).thenReturn(produtoReceitas);
        ResponseEntity<String> response = restTemplate.getForEntity("/produtosReceitas", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }


    @Test
    public void salva_produtoReceita(){
        ProdutoReceita produtoReceita = new ProdutoReceita( 2L,2L, 5.0);
        BDDMockito.when(produtoReceitaRepository.save(produtoReceita)).thenReturn(produtoReceita);
        ResponseEntity<String> response = restTemplate.postForEntity("/produtosReceitas", produtoReceita, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void deleta_produtoReceita_por_id(){
        BDDMockito.doNothing().when(produtoReceitaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/produtosReceitas/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_produtoReceita_por_id_inexistente(){
        BDDMockito.doNothing().when(produtoReceitaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/produtosReceitas/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
