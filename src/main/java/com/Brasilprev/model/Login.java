package com.Brasilprev.model;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import lombok.Data;

@Data
public class Login {
	 @NotBlank
	 private String email;

	 @NotBlank
	 private String password;

	 public Authentication getAuthentication() {
	     return new UsernamePasswordAuthenticationToken(this.email, this.password);
	 }

}
