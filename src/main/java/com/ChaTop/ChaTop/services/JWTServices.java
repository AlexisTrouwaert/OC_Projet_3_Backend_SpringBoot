package com.ChaTop.ChaTop.services;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class JWTServices {

	private JwtEncoder jwtEncoder;
	private final JwtDecoder jwtDecoder;
	
	public JWTServices(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
		this.jwtEncoder = jwtEncoder;
		this.jwtDecoder =jwtDecoder;
	}
	
	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		JwtClaimsSet claims = JwtClaimsSet.builder()
				.issuer("self")
				.issuedAt(now)
				.expiresAt(now.plus(1, ChronoUnit.DAYS))
				.subject(authentication.getName())
				.build();
		
		JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
				JwsHeader.with(MacAlgorithm.HS256).build(), claims
				);
		return this.jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
	}
	
	public String extractUsername(String token) {
	    return (String) jwtDecoder.decode(token).getClaims().get("sub");
	}
	
	public boolean isTokenExpired(String token) {
	    Instant expiration = jwtDecoder.decode(token).getExpiresAt();
	    return expiration != null && expiration.isBefore(Instant.now());
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
	    try {
	        String email = extractUsername(token); // Récupère l'email depuis le token

	        return email.equals(userDetails.getUsername()) && !isTokenExpired(token); // Comparaison + expiration
	    } catch (Exception e) {
	        return false;
	    }
	}
}
