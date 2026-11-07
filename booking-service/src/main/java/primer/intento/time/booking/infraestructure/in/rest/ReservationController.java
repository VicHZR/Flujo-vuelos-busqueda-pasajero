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

import java.util.List;
import java.util.stream.Collectors;

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

        // 2. Mapear DTO de entrada a Modelo de Dominio
        LodgingReservation reservationDomain = reservationMapper.toDomain(requestDto);

        // 3. Ejecutar el caso de uso de negocio
        LodgingReservation createdReservation = manageReservationUseCase.createReservation(reservationDomain);

        // 4. Mapear el Dominio resultante a DTO de respuesta
        ReservationResponseDto responseDto = reservationMapper.toResponseDto(createdReservation);

        // 5. Retornar respuesta exitosa
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {

        // 1. Validar el token JWT de forma remota
        if (!tokenValidationPort.validateToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o no autorizado para consultar la reserva.");
        }

        // 2. Obtener la reserva a través del caso de uso
        LodgingReservation reservationDomain = manageReservationUseCase.getReservation(id);

        // 3. Mapear a DTO de respuesta
        ReservationResponseDto responseDto = reservationMapper.toResponseDto(reservationDomain);

        // 4. Retornar 200 OK con los datos
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<?> getAllReservations(@RequestHeader("Authorization") String authHeader) {

        // 1. Validar el token JWT de forma remota
        if (!tokenValidationPort.validateToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o no autorizado para listar las reservas.");
        }

        // 2. Obtener todas las reservas a través del caso de uso
        List<LodgingReservation> reservations = manageReservationUseCase.getAllReservations();

        // 3. Mapear la lista de dominio a una lista de DTOs de respuesta
        List<ReservationResponseDto> responseDtos = reservations.stream()
                .map(reservationMapper::toResponseDto)
                .collect(Collectors.toList());

        // 4. Retornar la lista con código 200 OK
        return ResponseEntity.ok(responseDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id,
            @Valid @RequestBody ReservationRequestDto requestDto) {

        // 1. Validar el token JWT de forma remota
        if (!tokenValidationPort.validateToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o no autorizado para actualizar la reserva.");
        }

        // 2. Mapear DTO a dominio
        LodgingReservation reservationDomain = reservationMapper.toDomain(requestDto);

        // 3. Ejecutar la actualización en el caso de uso
        LodgingReservation updatedReservation = manageReservationUseCase.updateReservation(id, reservationDomain);

        // 4. Mapear respuesta
        ReservationResponseDto responseDto = reservationMapper.toResponseDto(updatedReservation);

        // 5. Retornar 200 OK con los datos actualizados
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(
            @RequestHeader("Authorization") String authHeader,
            @PathVariable Long id) {

        // 1. Validar el token JWT de forma remota
        if (!tokenValidationPort.validateToken(authHeader)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Token inválido o no autorizado para eliminar la reserva.");
        }

        // 2. Ejecutar la eliminación a través del caso de uso
        manageReservationUseCase.deleteReservation(id);

        // 3. Retornar respuesta exitosa
        return ResponseEntity.ok("Reserva con ID " + id + " eliminada exitosamente.");
    }
}