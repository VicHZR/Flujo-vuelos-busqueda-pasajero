package primer.intento.time.trip.infrastructure.adapter.in.web;

import primer.intento.time.trip.application.port.in.CreateTripCommand;
import primer.intento.time.trip.application.port.in.CreateTripUseCase;
import primer.intento.time.trip.domain.model.Trip;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/trips")
public class TripController {

    private final CreateTripUseCase createTripUseCase;

    public TripController(CreateTripUseCase createTripUseCase) {
        this.createTripUseCase = createTripUseCase;
    }

    @PostMapping
    public ResponseEntity<Trip> create(@RequestBody TripRequestDto dto) {
        CreateTripCommand command = new CreateTripCommand(dto.origin(), dto.destination(), dto.price());
        Trip created = createTripUseCase.createTrip(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

record TripRequestDto(String origin, String destination, BigDecimal price) {}