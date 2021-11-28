package com.Brasilprev.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.Brasilprev.entity.Client;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class TokenService {
	
	@Value("${brasilprev.jwt.duration}")
	private long duration;
	
	@Value("${brasilprev.jwt.secret}")
	private String secret;
	
	public String createToken(Authentication authentication) {
		Client client = (Client) authentication.getPrincipal();
		
		Date today = new Date();
        Date expirationDate = new Date(today.getTime() + duration);
        
        String token = Jwts.builder().setIssuer("Brasilprev API")
        		.setSubject(client.getCpf().toString())
        		.setIssuedAt(today)
        		.setExpiration(expirationDate)
        		.signWith(SignatureAlgorithm.HS256,secret)
        		.compact();
        		
        return token;
	}
	
	public boolean valid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException e) {
            return false;
        }
    }

    public String getUserId(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
