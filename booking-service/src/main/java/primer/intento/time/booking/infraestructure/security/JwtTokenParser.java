package primer.intento.time.booking.infraestructure.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class JwtTokenParser {

    private final SecretKey key;

    public JwtTokenParser() {

        String secretKey = "tu-clave-secreta-super-compartida-y-segura-de-al-menos-32-caracteres";
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
    }

    public Optional<JwtPrincipal> parse(String token) {
        try {
            Claims claims = Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            Long uid = claims.get("id", Long.class);
            String username = claims.getSubject();
            String role = claims.get("role", String.class);

            return Optional.of(new JwtPrincipal(uid, username, role));
        } catch (Exception e) {
            System.out.println("ERROR AL PARSEAR JWT: " + e.getMessage());
            return Optional.empty();
        }
    }
}