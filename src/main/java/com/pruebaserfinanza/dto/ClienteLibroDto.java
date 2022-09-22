package com.pruebaserfinanza.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ClienteLibroDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellido;
	
	@NotBlank
	private String identificacion;
	
	@NotNull
	private Date fechaNacimiento;
	
	@NotBlank
	private String numeroCelular;
	
	@NotNull
	private Date fechaPrestamo;
	
	@NotNull
	private Date fechaFinPrestamo;
	
	@NotNull
	private Long idLib;
}
