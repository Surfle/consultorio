package com.consultorio.consultorio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.consultorio.consultorio.dto.LoginDTO;
import com.consultorio.consultorio.dto.UserDTO;
import com.consultorio.consultorio.service.LoginService;

@RestController
@RequestMapping
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/login")
	public ResponseEntity<UserDTO> logar(@RequestBody LoginDTO loginDTO) {
		try {
			return ResponseEntity.ok(loginService.logar(loginDTO));
		}catch(AuthenticationException ex) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
	}


	@GetMapping("/deslogar")
	public ResponseEntity<HttpStatus> logout() {

		SecurityContextHolder.clearContext();
		return new ResponseEntity<>(null, HttpStatus.OK);

	}

   
	
}
