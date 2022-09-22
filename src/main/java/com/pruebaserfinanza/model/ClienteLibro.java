package com.pruebaserfinanza.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cliente_libro")
@Getter
@Setter
@NoArgsConstructor
public class ClienteLibro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nombre;
	
	private String apellido;
	
	private String identificacion;
	
	@Column(name = "fecha_nacimiento")
	private Date fechaNacimiento;
	
	@Column(name = "numero_celular")
	private String numeroCelular;
	
	@Column(name = "fecha_prestamo")
	private Date fechaPrestamo;
	
	@Column(name = "fecha_fin_prestamo")
	private Date fechaFinPrestamo;
	
	@OneToOne
	@JoinColumn(name = "libro_id", referencedColumnName = "id_lib", unique = true)
	private Libro libro;	
	
	public ClienteLibro(String nombre, String apellido, String identificacion, Date fechaNacimiento,
			String numeroCelular, Date fechaPrestamo, Date fechaFinPrestamo) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.identificacion = identificacion;
		this.fechaNacimiento = fechaNacimiento;
		this.numeroCelular = numeroCelular;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaFinPrestamo = fechaFinPrestamo;
	}

}
