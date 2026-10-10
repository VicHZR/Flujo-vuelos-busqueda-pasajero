package primer.intento.time.infraestructure.adapter.out.security;

import primer.intento.time.application.port.out.TokenIssuerPort;
import primer.intento.time.infraestructure.adapter.in.web.security.JwtProvider;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtTokenAdapter implements TokenIssuerPort {

    private final JwtProvider jwtProvider;

    public JwtTokenAdapter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public String generateToken(String email, String role) {

        return jwtProvider.generateToken(email, List.of(role));
    }

    @Override
    public boolean validateToken(String token) {
        return jwtProvider.validateToken(token);
    }

    @Override
    public String extractUsername(String token) {

        return jwtProvider.getUsernameFromToken(token);
    }
}