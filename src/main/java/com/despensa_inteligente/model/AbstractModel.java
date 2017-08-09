package com.despensa_inteligente.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@SuppressWarnings("serial")
@MappedSuperclass
@Data
public class AbstractModel implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	
	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}
	
}
