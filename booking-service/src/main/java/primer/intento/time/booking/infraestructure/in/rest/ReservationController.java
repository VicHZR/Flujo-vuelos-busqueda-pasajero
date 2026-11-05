package primer.intento.time.booking.infraestructure.in.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import primer.intento.time.booking.application.port.in.ManageReservationUseCase;
import primer.intento.time.booking.application.port.out.TokenValidationPort;
import primer.intento.time.booking.domain.model.LodgingReservation;
import primer.intento.time.booking.infraestructure.web.dto.ReservationRequestDto;
import primer.intento.time.booking.infraestructure.web.dto.ReservationResponseDto;
import primer.intento.time.booking.infraestructure.web.mapper.ReservationMapper;

@RestController
@RequestMapping("/api/v1/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final TokenValidationPort tokenValidationPort;
    private final ManageReservationUseCase manageReservationUseCase;
    private final ReservationMapper reservationMapper;

    @GetMapping("/test-auth")
    public ResponseEntity<String> testAuthCommunication(@RequestHeader("Authorization") String authHeader) {
        boolean isValid = tokenValidationPort.validateToken(authHeader);

        if (isValid) {
            return ResponseEntity.ok("¡Comunicación exitosa! El token es válido según el auth-service.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token inválido o error en la comunicación con auth-service.");
        }
    }

    @PostMapping
    public ResponseEntity<?> createReservation(
            @RequestHeader("Authorization") String authHeader,
            @Valid @RequestBody ReservationRequestDto requestDto) {

        // 1. Validar el token JWT de forma remota mediante OpenFeign
        if (!tokenValidationPort.validateToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o no autorizado para crear la reserva.");
        }

        // 2. Mapear DTO de entrada a Modelo de Dominio usando el mapper web
        LodgingReservation reservationDomain = reservationMapper.toDomain(requestDto);

        // 3. Ejecutar el caso de uso de negocio
        LodgingReservation createdReservation = manageReservationUseCase.createReservation(reservationDomain);

        // 4. Mapear el Dominio resultante a DTO de respuesta
        ReservationResponseDto responseDto = reservationMapper.toResponseDto(createdReservation);

        // 5. Retornar respuesta exitosa con la reserva creada
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}