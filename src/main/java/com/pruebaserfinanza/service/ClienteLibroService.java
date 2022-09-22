package com.pruebaserfinanza.service;

import java.util.List;

import com.pruebaserfinanza.dto.ClienteLibroDto;
import com.pruebaserfinanza.exception.PalindromoException;
import com.pruebaserfinanza.model.ClienteLibro;

public interface ClienteLibroService {

	public List<ClienteLibro> listaClienteLibro();
	
	public ClienteLibro obtenerClienteLibro(Long id);
	
	public ClienteLibro guardarRegistroPrestamo(ClienteLibroDto clienteLibroDto);
	
	public ClienteLibro actualizarRegistroPrestamo(ClienteLibro clienteLibro);
	
	public boolean eliminarRegistroPrestamo(Long id);
	
	public void numeroPalindromo(Long idLib) throws PalindromoException;
	
}
