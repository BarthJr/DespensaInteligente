package com.despensa_inteligente.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;

@SuppressWarnings("serial")
@Entity
//@Data
@EqualsAndHashCode(callSuper = false)
public class Receita extends AbstractModel {

	private String titulo;
	private String modoPreparo;
	private LocalDateTime tempoExecucao;
	private Long quantidade;
	
	@ManyToOne
	private Cliente cliente;
	
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
	public LocalDateTime getTempoExecucao() {
		return tempoExecucao;
	}
	public void setTempoExecucao(LocalDateTime tempoExecucao) {
		this.tempoExecucao = tempoExecucao;
	}
	public Long getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}
}
