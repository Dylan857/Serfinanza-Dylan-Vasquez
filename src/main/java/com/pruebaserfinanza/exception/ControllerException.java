package com.pruebaserfinanza.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerException {

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidateExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		return errors;
	}
	
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PalindromoException.class)
	public Map<String, String> numeroPalindromoException(PalindromoException ex) {
		Map<String, String> error = new HashMap<>();
		String errorTitulo = "Mensaje";
		String mensaje = "Los libros palíndromos solo se pueden utilizar en la biblioteca";
		error.put(errorTitulo, mensaje);
		return error;
	}
	
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public Map<String, String> handleValidateExceptionsUnique(SQLIntegrityConstraintViolationException ex) {
		Map<String, String> errors = new HashMap<>();
		String mensaje = "Mensaje";
		String cuerpoSplit = ex.getMessage();
		String[] partes = cuerpoSplit.split(" ");
		String cuerpo = "El libro con el IdLib: " + partes[2] + " ya se encuentra en prestamo";
		errors.put(mensaje, cuerpo);
		return errors;
	}
	
}
