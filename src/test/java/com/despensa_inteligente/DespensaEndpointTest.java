package com.despensa_inteligente;

import com.despensa_inteligente.model.Cliente;
import com.despensa_inteligente.model.Despensa;
import com.despensa_inteligente.model.Produto;
import com.despensa_inteligente.model.ProdutoDespensa;
import com.despensa_inteligente.repository.DespensaRepository;
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

public class DespensaEndpointTest extends DespensaInteligenteApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private DespensaRepository despensaRepository;

    Cliente cliente;
    List<ProdutoDespensa> produtosDespensas;
    ProdutoDespensa produtoDespensa;
    Despensa despensa;

    @Before
    public void setUp() throws Exception {
        cliente = new Cliente(1L);
        despensa = new Despensa(1L, "Ap", "Floripa", cliente);
        BDDMockito.when(despensaRepository.findOne(despensa.getId())).thenReturn(despensa);
    }

    @Test
    public void lista_despensas(){
        List<Despensa> despensas = asList(despensa,
                new Despensa(2L,"Casa", "Rio")
        );
        BDDMockito.when(despensaRepository.findAll()).thenReturn(despensas);
        ResponseEntity<String> response = restTemplate.getForEntity("/despensas", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void procura_despensa_por_id(){
        ResponseEntity<Despensa> response = restTemplate.getForEntity("/despensas/{id}", Despensa.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }


    @Test
    public void salva_despensa(){
        Despensa despensa = new Despensa("nomeDespensa", "Vancouver", cliente);
        BDDMockito.when(despensaRepository.save(despensa)).thenReturn(despensa);
        ResponseEntity<String> response = restTemplate.postForEntity("/despensas", despensa, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();

    }

    @Test
    public void deleta_despensa_por_id(){
        BDDMockito.doNothing().when(despensaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/despensas/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_despensa_por_id_inexistente(){
        BDDMockito.doNothing().when(despensaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/despensas/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
