package primer.intento.time.flight.infraestructure.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import primer.intento.time.flight.infraestructure.persistence.entity.FlightEntity;
import primer.intento.time.flight.infraestructure.persistence.repository.SpringDataFlightRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/flights")
public class FlightController {

    private final SpringDataFlightRepository flightRepository;

    public FlightController(SpringDataFlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    // 1. Crear un nuevo vuelo (POST)
    @PostMapping
    public ResponseEntity<FlightEntity> createFlight(@RequestBody FlightEntity flight) {
        FlightEntity savedFlight = flightRepository.save(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFlight);
    }

    // 2. Listar todos los vuelos (GET)
    @GetMapping
    public ResponseEntity<List<FlightEntity>> getAllFlights() {
        List<FlightEntity> flights = flightRepository.findAll();
        return ResponseEntity.ok(flights);
    }

    // 3. Buscar vuelo por su número de vuelo, ej: LA-2455 (GET)
    @GetMapping("/{flightNumber}")
    public ResponseEntity<FlightEntity> getFlightByNumber(@PathVariable String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 4. Buscar vuelo por ID interno de MongoDB (GET)
    @GetMapping("/id/{id}")
    public ResponseEntity<FlightEntity> getFlightById(@PathVariable String id) {
        return flightRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 5. Actualización completa de un vuelo por ID de MongoDB (PUT)
    @PutMapping("/id/{id}")
    public ResponseEntity<FlightEntity> updateFlight(@PathVariable String id, @RequestBody FlightEntity flightDetails) {
        return flightRepository.findById(id)
                .map(existingFlight -> {
                    existingFlight.setFlightNumber(flightDetails.getFlightNumber());
                    existingFlight.setAirline(flightDetails.getAirline());
                    existingFlight.setOrigin(flightDetails.getOrigin());
                    existingFlight.setDestination(flightDetails.getDestination());
                    existingFlight.setFlightTime(flightDetails.getFlightTime());
                    existingFlight.setStatus(flightDetails.getStatus());

                    FlightEntity updated = flightRepository.save(existingFlight);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 6. Actualización parcial de un vuelo por ID de MongoDB (PATCH)
    @PatchMapping("/id/{id}")
    public ResponseEntity<FlightEntity> partialUpdateFlight(@PathVariable String id, @RequestBody Map<String, Object> updates) {
        return flightRepository.findById(id)
                .map(existingFlight -> {
                    updates.forEach((key, value) -> {
                        switch (key) {
                            case "flightNumber" -> existingFlight.setFlightNumber((String) value);
                            case "airline" -> existingFlight.setAirline((String) value);
                            case "origin" -> existingFlight.setOrigin((String) value);
                            case "destination" -> existingFlight.setDestination((String) value);
                            case "status" -> existingFlight.setStatus((String) value);
                        }
                    });
                    FlightEntity updated = flightRepository.save(existingFlight);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // 7. Eliminar un vuelo por ID de MongoDB (DELETE)
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable String id) {
        if (flightRepository.existsById(id)) {
            flightRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}