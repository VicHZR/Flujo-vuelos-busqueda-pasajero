package primer.intento.time.infraestructure.adapter.in.web.controller;

import jakarta.validation.Valid;
import primer.intento.time.application.port.in.ManageReservationUseCase;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.infraestructure.adapter.in.web.dto.ReservationRequestDto;
import primer.intento.time.infraestructure.adapter.in.web.dto.ReservationResponseDto;
import primer.intento.time.infraestructure.adapter.in.web.mapper.ReservationMapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ManageReservationUseCase manageReservationUseCase;

    public ReservationController(ManageReservationUseCase manageReservationUseCase) {
        this.manageReservationUseCase = manageReservationUseCase;
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> createReservation(@Valid @RequestBody ReservationRequestDto requestDto) {
        LodgingReservation reservation = ReservationMapper.toDomain(requestDto);
        LodgingReservation created = manageReservationUseCase.createReservation(reservation);
        return ResponseEntity.status(HttpStatus.CREATED).body(ReservationMapper.toResponseDto(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> getReservation(@PathVariable Long id) {
        LodgingReservation reservation = manageReservationUseCase.getReservation(id);
        return ResponseEntity.ok(ReservationMapper.toResponseDto(reservation));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> getAllReservations() {
        List<ReservationResponseDto> reservations = manageReservationUseCase.getAllReservations()
                .stream()
                .map(ReservationMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(reservations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDto> updateReservation(@PathVariable Long id, @Valid @RequestBody ReservationRequestDto requestDto) {
        LodgingReservation reservation = ReservationMapper.toDomain(requestDto);
        LodgingReservation updated = manageReservationUseCase.updateReservation(id, reservation);
        return ResponseEntity.ok(ReservationMapper.toResponseDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        manageReservationUseCase.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}