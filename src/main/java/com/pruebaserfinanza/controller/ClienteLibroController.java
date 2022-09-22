package com.pruebaserfinanza.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaserfinanza.dto.ClienteLibroDto;
import com.pruebaserfinanza.exception.PalindromoException;
import com.pruebaserfinanza.model.ClienteLibro;
import com.pruebaserfinanza.model.Libro;
import com.pruebaserfinanza.service.ClienteLibroService;
import com.pruebaserfinanza.service.LibroService;

@RestController
@RequestMapping("/api/prestamo")
@CrossOrigin(origins = "http://localhost:8080/")
public class ClienteLibroController {

	@Autowired
	private ClienteLibroService clienteLibroService;

	@Autowired
	private LibroService libroService;

	@GetMapping("/prestamos")
	public ResponseEntity<List<ClienteLibro>> listaPrestamos() {
		return new ResponseEntity<>(clienteLibroService.listaClienteLibro(), HttpStatus.OK);
	}

	@PostMapping("/guardar")
	public ResponseEntity<ClienteLibro> guardarPrestamo(@Valid @RequestBody ClienteLibroDto clienteLibroDto) throws PalindromoException {
			clienteLibroService.numeroPalindromo(clienteLibroDto.getIdLib());
			return new ResponseEntity<>(clienteLibroService.guardarRegistroPrestamo(clienteLibroDto), HttpStatus.OK);
	}

	@PutMapping("/editarPrestamo/{id}")
	public ResponseEntity<ClienteLibro> actualizarPrestamo(@PathVariable("id") Long id,
			@Valid @RequestBody ClienteLibroDto clienteLibroDto) {
		ClienteLibro clienteLibroEncontrado = clienteLibroService.obtenerClienteLibro(id);

		if (clienteLibroEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {

			Libro libro = libroService.obtenerLibro(clienteLibroDto.getIdLib());
			clienteLibroEncontrado.setNombre(clienteLibroDto.getNombre());
			clienteLibroEncontrado.setApellido(clienteLibroDto.getApellido());
			clienteLibroEncontrado.setIdentificacion(clienteLibroDto.getIdentificacion());
			clienteLibroEncontrado.setFechaNacimiento(clienteLibroDto.getFechaNacimiento());
			clienteLibroEncontrado.setNumeroCelular(clienteLibroDto.getNumeroCelular());
			clienteLibroEncontrado.setFechaPrestamo(clienteLibroDto.getFechaPrestamo());
			clienteLibroEncontrado.setFechaFinPrestamo(clienteLibroDto.getFechaFinPrestamo());
			clienteLibroEncontrado.setLibro(libro);
			return new ResponseEntity<>(clienteLibroService.actualizarRegistroPrestamo(clienteLibroEncontrado),
					HttpStatus.OK);
		} catch (DataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/eliminarPrestamo/{id}")
	public ResponseEntity<Boolean> eliminarPrestamo(@PathVariable("id") Long id) {

		boolean prestamoEliminado = clienteLibroService.eliminarRegistroPrestamo(id);

		if (prestamoEliminado) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}