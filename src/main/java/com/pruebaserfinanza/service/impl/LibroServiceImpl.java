package com.pruebaserfinanza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pruebaserfinanza.dto.LibroDto;
import com.pruebaserfinanza.model.Libro;
import com.pruebaserfinanza.repository.LibroRepository;
import com.pruebaserfinanza.service.LibroService;

@Service
public class LibroServiceImpl implements LibroService {

	@Autowired
	private LibroRepository libroRepository;
	
	@Override
	public List<Libro> obtenerLibros() {
		return libroRepository.findAll();
	}

	@Override
	public Libro obtenerLibro(Long id) {
		
		Optional<Libro> libroOptional = libroRepository.findById(id);
		
		if (libroOptional.isPresent()) {
			return libroOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public Libro guardarLibro(LibroDto libroDto) {
		Libro libro = new Libro(libroDto.getIdLib(), libroDto.getNombre());
		return libroRepository.save(libro);
	}

	@Override
	public boolean eliminarLibro(Long idLib) {
		try {
			libroRepository.deleteById(idLib);
			return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Libro actualizarLibro(Libro libro) {
		return libroRepository.save(libro);
	}

}
