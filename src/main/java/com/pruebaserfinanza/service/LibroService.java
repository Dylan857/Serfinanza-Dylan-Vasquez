package com.pruebaserfinanza.service;

import java.util.List;

import com.pruebaserfinanza.dto.LibroDto;
import com.pruebaserfinanza.model.Libro;

public interface LibroService {

	public List<Libro> obtenerLibros();
	
	public Libro obtenerLibro(Long id);
	
	public Libro guardarLibro(LibroDto libroDto);
	
	public Libro actualizarLibro(Libro libro);
	
	public boolean eliminarLibro(Long idLib);
	
}
