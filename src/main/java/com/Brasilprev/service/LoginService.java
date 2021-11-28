package com.Brasilprev.service;

//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.Brasilprev.entity.Client;
//import com.Brasilprev.model.Login;
//import com.Brasilprev.repository.ClientRepository;

import lombok.AllArgsConstructor;

//@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginService {
	
//	private ClientRepository clientRepository;
//	
//	private AuthenticationManager authenticationManager;
//
//	private TokenService tokenService;
//	
//	
//	public ResponseEntity<Client> auth(Login login) throws UsernameNotFoundException {
//		String email = login.getEmail();
//		String password = login.getPassword();
//		
//		Authentication authLogin = getAuthentication(email, password);
//		
//		Authentication authentication = authenticationManager.authenticate(authLogin);
//		
//		String token = tokenService.createToken(authentication);
//		
//		Client client = clientRepository.findByEmail(email).get();
//			
////		Client getClient = new Client(client.getCpf(), client.getName(), client.getEmail(), client.getPassword(), client.getAddress(), token);
//		return ResponseEntity.ok(client);
//		
//	}
//	
//	public Authentication getAuthentication(String email, String password) {
//		return new UsernamePasswordAuthenticationToken(email, password);
//	}
}
