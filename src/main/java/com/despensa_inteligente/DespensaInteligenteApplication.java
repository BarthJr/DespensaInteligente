package com.despensa_inteligente;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.despensa_inteligente.model.ProdutoDespensa;

@EntityScan(basePackageClasses = {DespensaInteligenteApplication.class, Jsr310JpaConverters.class})
@SpringBootApplication
public class DespensaInteligenteApplication {

	public static void main(String[] args) {
		SpringApplication.run(DespensaInteligenteApplication.class, args);
	}
}
