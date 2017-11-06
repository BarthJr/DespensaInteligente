package com.despensa_inteligente;

import com.despensa_inteligente.model.*;
import com.despensa_inteligente.repository.ProdutoRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.*;

public class ProdutoEndpointTest extends DespensaInteligenteApplicationTests{

    private static final String NOME = "Macarraozinho";
    private static final String MARCA = "Todesquini";
    private static final String TIPO = "Com ovos";
    private static final Double PESO = 500.0;
    private static final Double QTD = 20.0;
    private static final String CATEGORIA_NOME = "test";

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ProdutoRepository produtoRepository;

    private Produto produto;
    List<Produto> produtos;

    @Before
    public void setUp() throws Exception {
        produto = new Produto(1L, NOME, MARCA, TIPO, PESO, 1L);
        BDDMockito.when(produtoRepository.findOne(produto.getId())).thenReturn(produto);
    }

    @Test
    public void procura_produto_por_id(){
        ResponseEntity<String> response = restTemplate.getForEntity("/produtos/{id}", String.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void lista_produtos(){
        produtos = new ArrayList<>(Arrays.asList(produto,
                new Produto(2L, NOME, MARCA, TIPO, PESO, 2L)
        ));
        BDDMockito.when(produtoRepository.findAll()).thenReturn(produtos);
        ResponseEntity<String> response = restTemplate.getForEntity("/produtos", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void salva_produto(){
        produto = new Produto(NOME, MARCA, TIPO, PESO, 1L);
        BDDMockito.when(produtoRepository.save(produto)).thenReturn(produto);
        ResponseEntity<String> response = restTemplate.postForEntity("/produtos", produto, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void deleta_produto_por_id(){
        BDDMockito.doNothing().when(produtoRepository).delete(1L);
        restTemplate.exchange("/produto/{id}", DELETE, null, String.class, 1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/produtos/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_produto_por_id_inexistente(){
        BDDMockito.doNothing().when(produtoRepository).delete(1L);
        restTemplate.exchange("/produto/{id}", DELETE, null, String.class, 1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/produtos/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
