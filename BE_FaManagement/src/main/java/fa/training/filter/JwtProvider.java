package fa.training.filter;

import java.util.Date;

import org.springframework.stereotype.Component;

import fa.training.entity.User;
import fa.training.model.UserDetailsDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
public class JwtProvider {

	private final String JWT_SECRET = "ABCDEFGH";

	private final long JWT_EXPIRATION = 604800000L;

	public String generateToken(UserDetailsDto userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

		User user = userDetails.getUser();
		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(now).setExpiration(expiryDate)
				.claim("role", user.getRole().getName()).claim("roleDescription", user.getRole().getDescription())
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET).compact();
	}

	public String getUserNameFromJwtToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
}
