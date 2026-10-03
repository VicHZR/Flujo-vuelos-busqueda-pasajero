package primer.intento.time.infraestructure.adapter.in.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {

    // Clave secreta (debe tener al menos 256 bits / 32 caracteres)
    private final String SECRET = "MiClaveSecretaSuperSeguraYMuyLargaParaPoderFirmarElToken123456";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .subject(username)                 // Reemplaza a setSubject
                .claim("roles", roles)
                .issuedAt(new Date())              // Reemplaza a setIssuedAt
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // Reemplaza a setExpiration
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()                          // Reemplaza a parserBuilder()
                    .verifyWith(key)                   // Nueva forma de asignar la llave de verificación
                    .build()
                    .parseSignedClaims(token);         // Reemplaza a parseClaimsJws
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()                      // Nueva forma de obtener el cuerpo (body)
                .getSubject();
    }

    @SuppressWarnings("unchecked")                 // Evita el warning "Unchecked assignment"
    public List<String> getRolesFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        return (List<String>) claims.get("roles");
    }
}