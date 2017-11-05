package com.despensa_inteligente;

import com.despensa_inteligente.model.Medida;
import com.despensa_inteligente.repository.MedidaRepository;
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

public class MedidaEndpointTest extends DespensaInteligenteApplicationTests{

    @Autowired
    private TestRestTemplate restTemplate;
    @MockBean
    private MedidaRepository medidaRepository;

    @Before
    public void setUp() throws Exception {
        Medida medida = new Medida(1L,"gramas", 5.0);
        BDDMockito.when(medidaRepository.findOne(medida.getId())).thenReturn(medida);
    }

    @Test
    public void procura_medida_por_id(){
        ResponseEntity<Medida> response = restTemplate.getForEntity("/medidas/{id}", Medida.class, 1L);
        assertThat(response.getStatusCode(), equalTo(OK));
    }

    @Test
    public void lista_medidas(){
        List<Medida> medidas = asList(new Medida(1L,"gramas", 5.0),
                new Medida(2L,"quilos", 10.0)
        );
        BDDMockito.when(medidaRepository.findAll()).thenReturn(medidas);
        ResponseEntity<String> response = restTemplate.getForEntity("/medidas", String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
    }


    @Test
    public void salva_medida(){
        Medida medida = new Medida("gramas", 5.0);
        BDDMockito.when(medidaRepository.save(medida)).thenReturn(medida);
        ResponseEntity<String> response = restTemplate.postForEntity("/medidas", medida, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
        Assertions.assertThat(response.getBody()).isNotEmpty();
    }

    @Test
    public void deleta_medida_por_id(){
        BDDMockito.doNothing().when(medidaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/medidas/{id}", DELETE, null, String.class, 1L);
        assertThat(exchange.getStatusCode(), equalTo(NO_CONTENT));
    }

    @Test
    public void deleta_medida_por_id_inexistente(){
        BDDMockito.doNothing().when(medidaRepository).delete(1L);
        ResponseEntity<String> exchange = restTemplate.exchange("/medidas/{id}", DELETE, null, String.class, -1L);
        assertThat(exchange.getStatusCode(), equalTo(NOT_FOUND));
    }
}
