package com.pruebaserfinanza.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pruebaserfinanza.dto.ClienteLibroDto;
import com.pruebaserfinanza.exception.PalindromoException;
import com.pruebaserfinanza.model.ClienteLibro;
import com.pruebaserfinanza.model.Libro;
import com.pruebaserfinanza.repository.ClienteLibroRepository;
import com.pruebaserfinanza.repository.LibroRepository;
import com.pruebaserfinanza.service.ClienteLibroService;

@Service
public class ClienteLibroServiceImpl implements ClienteLibroService {

	@Autowired
	private ClienteLibroRepository clienteLibroRepository;

	@Autowired
	private LibroRepository libroRepository;

	@Override
	@Transactional(readOnly = true)
	public List<ClienteLibro> listaClienteLibro() {
		return clienteLibroRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ClienteLibro obtenerClienteLibro(Long id) {
		Optional<ClienteLibro> clienteLibroOptional = clienteLibroRepository.findById(id);

		if (clienteLibroOptional.isPresent()) {
			return clienteLibroOptional.get();
		} else {
			return null;
		}
	}

	@Override
	@Transactional
	public ClienteLibro guardarRegistroPrestamo(ClienteLibroDto clienteLibroDto) {
		Optional<Libro> libroOptional = libroRepository.findById(clienteLibroDto.getIdLib());

		if (libroOptional.isPresent()) {
			Libro libro = libroOptional.get();
			ClienteLibro nuevoClienteLibro = new ClienteLibro(clienteLibroDto.getNombre(),
					clienteLibroDto.getApellido(), clienteLibroDto.getIdentificacion(),
					clienteLibroDto.getFechaNacimiento(), clienteLibroDto.getNumeroCelular(),
					clienteLibroDto.getFechaPrestamo(), clienteLibroDto.getFechaFinPrestamo());
			nuevoClienteLibro.setLibro(libro);
			return clienteLibroRepository.save(nuevoClienteLibro);
		} else {
			return null;
		}
	}

	@Override
	public boolean eliminarRegistroPrestamo(Long id) {
		try {
			clienteLibroRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void numeroPalindromo(Long idLib) throws PalindromoException {

		long n = idLib;

		long rev = 0;

		while (n > 0) {
			long r = n % 10;
			rev = rev * 10 + r;
			n = n / 10;
		}

		if (idLib == rev) {
			throw new PalindromoException("Los libros palíndromos solo se pueden utilizar en la biblioteca");
		}
	}

	@Override
	public ClienteLibro actualizarRegistroPrestamo(ClienteLibro clienteLibro) {
		return clienteLibroRepository.save(clienteLibro);
	}

}
