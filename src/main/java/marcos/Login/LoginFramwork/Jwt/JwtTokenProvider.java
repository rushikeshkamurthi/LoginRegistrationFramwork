package marcos.Login.LoginFramwork.Jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {

	 private final String secretKey = "yourSecretKey"; // Replace with your secret key
	    private final long tokenValidityDuration = 900_000; // 15 minutes

	    public String generateAccessToken(String username) {
	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + tokenValidityDuration);

	        Map<String, Object> claims = new HashMap();
	        claims.put("sub", username);

	        return Jwts.builder()
	                .setClaims(claims)
	                .setIssuedAt(now)
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS256, secretKey)
	                .compact();
	    }

	    // You can also implement a method to generate refresh tokens here

	    public String getUsernameFromToken(String token) {
	        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
	            return true;
	        } catch (SignatureException | MalformedJwtException | ExpiredJwtException | UnsupportedJwtException | IllegalArgumentException ex) {
	            return false;
	        }
	    }
}
