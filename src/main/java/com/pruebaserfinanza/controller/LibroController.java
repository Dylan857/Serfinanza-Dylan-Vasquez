package com.pruebaserfinanza.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pruebaserfinanza.dto.LibroDto;
import com.pruebaserfinanza.model.Libro;
import com.pruebaserfinanza.service.LibroService;

@RestController
@RequestMapping("/libro")
public class LibroController {

	@Autowired
	private LibroService libroService;
	
	@GetMapping("/libros")
	public ResponseEntity<List<Libro>> listaLibros() {
		return new ResponseEntity<>(libroService.obtenerLibros(), HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<Libro> guardarLibro(@Valid @RequestBody LibroDto libroDto) {
		return new ResponseEntity<>(libroService.guardarLibro(libroDto), HttpStatus.OK);
	}
	
	@PutMapping("/editarLibro/{id}")
	public ResponseEntity<Libro> actualizarLibro(@PathVariable("id") Long id, @Valid @RequestBody LibroDto libroDto) {
		Libro libroEncontrado = libroService.obtenerLibro(id);
		
		if (libroEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		try {
			libroEncontrado.setIdLib(libroDto.getIdLib());
			libroEncontrado.setNombre(libroDto.getNombre());
			return new ResponseEntity<>(libroService.actualizarLibro(libroEncontrado), HttpStatus.OK);
		} catch (DataAccessException ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/eliminarLibro/{id}")
	public ResponseEntity<Boolean> eliminarLibro(@PathVariable("id") Long id) {
		boolean libroEliminado = libroService.eliminarLibro(id);
		
		if (libroEliminado) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}