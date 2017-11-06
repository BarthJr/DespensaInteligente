package com.despensa_inteligente;

import com.despensa_inteligente.model.Categoria;
import com.despensa_inteligente.model.Cliente;
import com.despensa_inteligente.model.Receita;
import com.despensa_inteligente.repository.ReceitaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.*;

public class ReceitaEndpointTest extends DespensaInteligenteApplicationTests{

    private static final String TITULO = "Miojo";
    private static final String MODO_PREPARO = "3 minutos";
//    private static final Double TEMPO = 55;
    private static final Double QTD = 20.0;

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ReceitaRepository receitaRepository;

    private Receita receita;
    List<Receita> receitas;
    Cliente cliente;
    private Categoria categoria;

    @Before
    public void setUp() throws Exception {
        receita = new Receita(1L, TITULO, MODO_PREPARO, QTD, 2L);
        BDDMockito.when(receitaRepository.findOne(receita.getId())).thenReturn(receita);
    }

    @Test
    public void procura_receita_por_id(){
        ResponseEntity<String> response = restTemplate.getForEntity("/receitas/{id}", String.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void lista_receitas(){
        receitas = new ArrayList<>(Arrays.asList(receita,
                new Receita(2L, TITULO, MODO_PREPARO, QTD, 1L)
        ));
        BDDMockito.when(receitaRepository.findAll()).thenReturn(receitas);
        ResponseEntity<String> response = restTemplate.getForEntity("/receitas", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }




    @Test
    public void salva_receita(){
        receita = new Receita(3L, TITULO, MODO_PREPARO, QTD, 1L);
        BDDMockito.when(receitaRepository.save(receita)).thenReturn(receita);
        ResponseEntity<String> response = restTemplate.postForEntity("/receitas", receita, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void deleta_receita_por_id(){
        BDDMockito.doNothing().when(receitaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/receitas/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_receita_por_id_inexistente(){
        BDDMockito.doNothing().when(receitaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/receitas/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
