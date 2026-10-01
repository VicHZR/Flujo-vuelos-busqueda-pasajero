package primer.intento.time.security.web;

public record AuthRequestDto(
        String username,
        String password
) {}