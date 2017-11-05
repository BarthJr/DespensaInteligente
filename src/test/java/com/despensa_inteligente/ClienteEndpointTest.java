package com.despensa_inteligente;

import com.despensa_inteligente.model.Cliente;
import com.despensa_inteligente.DespensaInteligenteApplicationTests;
import com.despensa_inteligente.repository.ClienteRepository;
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

public class ClienteEndpointTest extends DespensaInteligenteApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private ClienteRepository clienteRepository;

    Cliente cliente;

    @Before
    public void setUp() throws Exception {
        cliente = new Cliente(1L, "nomeCliente", "ab", "12");
        BDDMockito.when(clienteRepository.findOne(cliente.getId())).thenReturn(cliente);
    }

    @Test
    public void lista_clientes(){
        List<Cliente> clientes = asList(cliente,
                new Cliente(2L,"nomeCliente", "abc", "123")
        );
        BDDMockito.when(clienteRepository.findAll()).thenReturn(clientes);
        ResponseEntity<String> response = restTemplate.getForEntity("/clientes", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void procura_cliente_por_id(){
        ResponseEntity<Cliente> response = restTemplate.getForEntity("/clientes/{id}", Cliente.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }


    @Test
    public void salva_cliente(){
        Cliente cliente = new Cliente("nomeCliente", "abcd", "1234");
        BDDMockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
        ResponseEntity<String> response = restTemplate.postForEntity("/clientes", cliente, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();

    }

    @Test
    public void deleta_cliente_por_id(){
        BDDMockito.doNothing().when(clienteRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/clientes/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_cliente_por_id_inexistente(){
        BDDMockito.doNothing().when(clienteRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/clientes/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
