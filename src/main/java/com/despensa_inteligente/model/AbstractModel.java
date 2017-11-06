package com.despensa_inteligente.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;
import lombok.ToString;

@SuppressWarnings("serial")
@MappedSuperclass
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbstractModel implements Serializable {
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
}
