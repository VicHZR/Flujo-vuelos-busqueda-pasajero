package primer.intento.time.infraestructure.adapter.in.web;

import primer.intento.time.application.port.in.ManageReservationUseCase;
import primer.intento.time.domain.model.LodgingReservation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ManageReservationUseCase useCase;

    public ReservationController(ManageReservationUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<LodgingReservation> createReservation(@RequestBody LodgingReservation reservation) {
        return new ResponseEntity<>(useCase.createReservation(reservation), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<LodgingReservation>> getAllReservations() {
        return ResponseEntity.ok(useCase.getAllReservations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LodgingReservation> getReservation(@PathVariable Long id) {
        return ResponseEntity.ok(useCase.getReservation(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LodgingReservation> updateReservation(@PathVariable Long id, @RequestBody LodgingReservation reservation) {
        return ResponseEntity.ok(useCase.updateReservation(id, reservation));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LodgingReservation> patchReservation(@PathVariable Long id, @RequestBody LodgingReservation reservation) {
        reservation.setId(id);
        return ResponseEntity.ok(useCase.updateReservation(id, reservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        useCase.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}