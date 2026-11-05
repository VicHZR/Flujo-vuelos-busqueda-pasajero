package primer.intento.time.booking.infraestructure.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import primer.intento.time.booking.infraestructure.config.FeignConfig; // <-- Corregido con 'e' (infraestructure)

@FeignClient(name = "auth-service", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface AuthClient {

    @GetMapping("/api/auth/verify")
    boolean verifyToken();
}