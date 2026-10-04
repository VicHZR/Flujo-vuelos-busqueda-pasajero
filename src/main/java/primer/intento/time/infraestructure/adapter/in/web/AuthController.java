package primer.intento.time.infraestructure.adapter.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import primer.intento.time.infraestructure.adapter.in.web.security.JwtProvider;
import primer.intento.time.infraestructure.adapter.in.web.security.dto.AuthRequest;
import primer.intento.time.infraestructure.adapter.in.web.security.dto.AuthResponse;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtProvider jwtProvider;

    public AuthController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {

        // Simulación temporal para probar Bruno:
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {

            List<String> roles = List.of("ROLE_ADMIN");

            String token = jwtProvider.generateToken(request.getUsername(), roles);

            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}