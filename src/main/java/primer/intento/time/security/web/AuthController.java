package primer.intento.time.security.web;

import primer.intento.time.security.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final JwtService jwtService;

    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto request) {
        // Validación simplificada para demostración
        if ("admin@time.com".equals(request.username()) && "admin123".equals(request.password())) {
            String token = jwtService.generateToken(request.username());
            return ResponseEntity.ok(new AuthResponseDto(token));
        }
        return ResponseEntity.status(401).build();
    }
}