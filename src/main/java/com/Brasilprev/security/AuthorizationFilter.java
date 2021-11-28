package com.Brasilprev.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Brasilprev.entity.Client;
import com.Brasilprev.repository.ClientRepository;
import com.Brasilprev.service.TokenService;

public class AuthorizationFilter extends OncePerRequestFilter{

	private TokenService tokenService;
	
	private ClientRepository clientRepository;
	
	
	public AuthorizationFilter(TokenService tokenService, ClientRepository clientRepository) {
		super();
		this.tokenService = tokenService;
		this.clientRepository = clientRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = extractToken(request);
		
		boolean valid = tokenService.valid(token);
		
		if(valid) authorize(token);
		
		filterChain.doFilter(request, response);
		
	}
	
	private void authorize(String token) {
		String id = tokenService.getUserId(token);
		Client client = clientRepository.findById(id).get();
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(client, null, client.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(auth);
	}
	
	 private String extractToken(HttpServletRequest request) {
	        String header = request.getHeader("Authorization");

	        if (header == null || header.isEmpty() || !header.startsWith("Bearer "))
	            return null;

	        return header.substring(7, header.length());
	    }
	
	
	
}
