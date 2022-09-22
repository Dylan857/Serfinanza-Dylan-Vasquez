package com.pruebaserfinanza.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pruebaserfinanza.model.ClienteLibro;

@Repository
public interface ClienteLibroRepository extends JpaRepository<ClienteLibro, Long>{
	
}
