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
        // TODO: Aquí buscarás el request.getUsername() en tu tabla de MySQL (UserEntity)
        // y verificarás que las contraseñas coincidan.

        // Simulación temporal para probar Bruno:
        if ("admin".equals(request.getUsername()) && "123456".equals(request.getPassword())) {

            // Simulamos que el usuario tiene el rol de administrador en tu base de datos
            List<String> roles = List.of("ROLE_ADMIN");

            // ¡Aquí se utiliza tu método! La advertencia del IDE desaparecerá.
            String token = jwtProvider.generateToken(request.getUsername(), roles);

            return ResponseEntity.ok(new AuthResponse(token));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}