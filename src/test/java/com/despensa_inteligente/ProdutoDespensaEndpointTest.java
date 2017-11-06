package com.despensa_inteligente;

import com.despensa_inteligente.model.ProdutoDespensa;
import com.despensa_inteligente.repository.ProdutoDespensaRepository;
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

public class ProdutoDespensaEndpointTest extends DespensaInteligenteApplicationTests{

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ProdutoDespensaRepository produtoDespensaRepository;

    ProdutoDespensa produtoDespensa;

    @Before
    public void setUp() throws Exception {
        produtoDespensa = new ProdutoDespensa(1L, 2L,1L, 5.0);
        BDDMockito.when(produtoDespensaRepository.findOne(produtoDespensa.getId())).thenReturn(produtoDespensa);
    }

    @Test
    public void procura_produtoDespensa_por_id(){
        ResponseEntity<ProdutoDespensa> response = restTemplate.getForEntity("/produtosDespensas/{id}", ProdutoDespensa.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void lista_produtoDespensas(){
        List<ProdutoDespensa> produtoDespensas = asList(produtoDespensa,
                new ProdutoDespensa(2L, 1L,2L, 15.0)
        );
        BDDMockito.when(produtoDespensaRepository.findAll()).thenReturn(produtoDespensas);
        ResponseEntity<String> response = restTemplate.getForEntity("/produtosDespensas", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }


    @Test
    public void salva_produtoDespensa(){
        ProdutoDespensa produtoDespensa = new ProdutoDespensa( 2L,2L, 5.0);
        BDDMockito.when(produtoDespensaRepository.save(produtoDespensa)).thenReturn(produtoDespensa);
        ResponseEntity<String> response = restTemplate.postForEntity("/produtosDespensas", produtoDespensa, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void deleta_produtoDespensa_por_id(){
        BDDMockito.doNothing().when(produtoDespensaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/produtosDespensas/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_produtoDespensa_por_id_inexistente(){
        BDDMockito.doNothing().when(produtoDespensaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/produtosDespensas/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
