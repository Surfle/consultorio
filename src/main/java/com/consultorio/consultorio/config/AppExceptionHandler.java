package com.consultorio.consultorio.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.consultorio.consultorio.dto.MensagemDTO;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(value = UsernameNotFoundException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public ResponseEntity<MensagemDTO> usernameNotFoundException(UsernameNotFoundException ex) {
		MensagemDTO message = new MensagemDTO(ex.getMessage());
		return new ResponseEntity<>(message, HttpStatus.UNAUTHORIZED);
	}
}