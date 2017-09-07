package com.despensa_inteligente.model;

import java.time.LocalDate;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Receita extends AbstractModel {

	private String titulo;
	private String modoPreparo;
	private LocalDate tempoExecucao;
	private Long quantidade;
	
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getModoPreparo() {
		return modoPreparo;
	}
	public void setModoPreparo(String modoPreparo) {
		this.modoPreparo = modoPreparo;
	}
	public LocalDate getTempoExecucao() {
		return tempoExecucao;
	}
	public void setTempoExecucao(LocalDate tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}
