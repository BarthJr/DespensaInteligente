package com.despensa_inteligente.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Receita extends AbstractModel {

	private String titulo;
	private String modoPreparo;
	private LocalDateTime tempoExecucao;
	private Long quantidade;
	
	@ManyToOne
	private Cliente cliente;
	
}
