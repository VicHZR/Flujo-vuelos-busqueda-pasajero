package primer.intento.time.booking.infraestructure.out.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import primer.intento.time.booking.application.port.out.TokenValidationPort;
import primer.intento.time.booking.infraestructure.out.feign.AuthClient;

@Component
@RequiredArgsConstructor
public class AuthClientAdapter implements TokenValidationPort {

    private final AuthClient authClient;

    @Override
    public boolean validateToken(String token) {
        try {
            // Se llama sin parámetros porque el interceptor de Feign inyecta el token en la cabecera
            return authClient.verifyToken();
        } catch (Exception e) {
            return false;
        }
    }
}