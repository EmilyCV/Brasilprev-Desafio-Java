package com.Brasilprev.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Brasilprev.model.Login;
import com.Brasilprev.service.TokenService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {
	private AuthenticationManager authenticationManager;
	
	private TokenService tokenService;
	
	@PostMapping
    @ResponseStatus(HttpStatus.OK)
	public ResponseEntity<String> auth(@RequestBody @Valid Login login) {
		Authentication auth = login.getAuthentication();
		try {
			Authentication authentication = authenticationManager.authenticate(auth);
			String token = tokenService.createToken(authentication);
			return ResponseEntity.ok(token);
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
