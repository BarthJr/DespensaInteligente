package com.despensa_inteligente.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass
//@Data
public class AbstractModel implements Serializable {
	@Id
	@GeneratedValue
	private Long id;
	
	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
	    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
	}

	
}
