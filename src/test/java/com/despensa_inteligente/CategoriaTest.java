//package com.despensa_inteligente;
//
//
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.web.client.RestTemplate;
//
//import com.despensa_inteligente.model.Categoria;
//
//public class CategoriaTest {
//	public static void main(String[] args) {
//		RestTemplate restTemplate =  new RestTemplateBuilder()
//				.rootUri("http://localhost:8080/categorias");
//		Categoria categoria = restTemplate.getForObject(url:"/{1}", Categoria.class);
//		System.out.println(categoria);
//				
//	}
//
//}
