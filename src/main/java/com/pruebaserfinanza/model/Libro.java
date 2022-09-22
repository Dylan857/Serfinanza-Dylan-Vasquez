package com.pruebaserfinanza.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Libro implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_lib")
	private Long idLib;
	
	private String nombre;
	
}
