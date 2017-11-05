package com.despensa_inteligente;

import com.despensa_inteligente.model.Categoria;
import com.despensa_inteligente.repository.CategoriaRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

public class CategoriaEndpointTest extends DespensaInteligenteApplicationTests{

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private CategoriaRepository categoriaRepository;

    @Before
    public void setUp() throws Exception {
        Categoria categoria = new Categoria(1L,"Test");
        BDDMockito.when(categoriaRepository.findOne(categoria.getId())).thenReturn(categoria);
    }

    @Test
    public void lista_categorias(){
        List<Categoria> categorias = asList(new Categoria(1L, "Test1"),
                new Categoria(2L, "Test2")
        );
        BDDMockito.when(categoriaRepository.findAll()).thenReturn(categorias);
        ResponseEntity<String> response = restTemplate.getForEntity("/categorias", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void procura_categoria_por_id(){
        ResponseEntity<Categoria> response = restTemplate.getForEntity("/categorias/{id}", Categoria.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }


    @Test
    public void salva_categoria(){
        Categoria categoria = new Categoria("Salva");
        BDDMockito.when(categoriaRepository.save(categoria)).thenReturn(categoria);
        ResponseEntity<String> response = restTemplate.postForEntity("/categorias", categoria, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void deleta_categoria_por_id(){
        BDDMockito.doNothing().when(categoriaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/categorias/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_categoria_por_id_inexistente(){
        BDDMockito.doNothing().when(categoriaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/categorias/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
