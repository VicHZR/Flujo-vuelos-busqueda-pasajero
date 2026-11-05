package primer.intento.time.auth.infraestructure.in.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import primer.intento.time.auth.infraestructure.in.web.dto.LoginRequestDto;
import primer.intento.time.auth.infraestructure.in.web.dto.TokenResponseDto;
import primer.intento.time.auth.infraestructure.persistence.UserEntity;
import primer.intento.time.auth.infraestructure.persistence.UserRepository;
import primer.intento.time.auth.infraestructure.security.JwtTokenIssuer;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenIssuer tokenIssuer;

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenIssuer tokenIssuer) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenIssuer = tokenIssuer;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequestDto request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("El usuario ya existe");
        }
        UserEntity newUser = new UserEntity();
        newUser.setUsername(request.getUsername());
        // Aquí el passwordEncoder cifra correctamente el texto plano que envíes
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole("USER");

        userRepository.save(newUser);
        return ResponseEntity.ok("Usuario registrado exitosamente");
    }


    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody LoginRequestDto request) {
        // 1. Buscar usuario en auth_db
        UserEntity user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        // 2. Validar contraseña
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // 3. Generar el token con JwtTokenIssuer
        String token = tokenIssuer.generateToken(user.getId(), user.getUsername(), user.getRole());

        return ResponseEntity.ok(new TokenResponseDto(token));
    }

    // 4. Endpoint para verificar tokens vía OpenFeign desde el booking-service
    @GetMapping("/verify")
    public ResponseEntity<Boolean> verifyToken(@RequestHeader(value = "Authorization", required = false) String authHeader) {
        try {
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.ok(false);
            }

            String token = authHeader.substring(7);

            // Valida el token usando tu JwtTokenIssuer
            boolean isValid = tokenIssuer.validateToken(token);

            return ResponseEntity.ok(isValid);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
    }
}