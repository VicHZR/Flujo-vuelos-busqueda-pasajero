package primer.intento.time.booking.application.port.out;

public interface TokenValidationPort {
    boolean validateToken(String token);
}
