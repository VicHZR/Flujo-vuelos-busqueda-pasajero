package primer.intento.time.application.port.out;

public interface TokenIssuerPort {
    String generateToken(String email, String role);
    boolean validateToken(String token);
    String extractUsername(String token);
}