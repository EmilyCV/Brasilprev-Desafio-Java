package com.Brasilprev.service;

import org.springframework.stereotype.Service;

import com.Brasilprev.entity.Client;
import com.Brasilprev.exception.ClientNotFoundException;
import com.Brasilprev.repository.ClientRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationService implements UserDetailsService{
		
	private ClientRepository clientRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<Client> client = clientRepository.findByEmail(email);
		if(!client.isPresent()) {
			throw new ClientNotFoundException();
		}
		return client.get();
	}
	
	public static PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
