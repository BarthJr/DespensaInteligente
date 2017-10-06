//package com.despensa_inteligente;
//
//import org.junit.Rule;
//import org.junit.rules.ExpectedException;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import com.despensa_inteligente.model.Produto;
//import com.despensa_inteligente.repository.ProdutoRepository;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//public class ProdutoRepositoryTest {
//	@Autowired
//	private ProdutoRepository produtoRepository;
//	@Rule
//	public ExpectedException thrown = ExpectedException.none();
//	
//	public void createShouldPersistData() {
//		Produto produto = new Produto(nome:"Choc", marca:"Nescau", tipo:"ao leite");
//	}
//
//}
