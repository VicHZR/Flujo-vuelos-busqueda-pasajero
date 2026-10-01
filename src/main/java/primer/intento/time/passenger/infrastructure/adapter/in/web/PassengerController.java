package primer.intento.time.passenger.infrastructure.adapter.in.web;

import jakarta.validation.Valid;
import primer.intento.time.passenger.application.port.in.CreatePassengerCommand;
import primer.intento.time.passenger.application.port.in.CreatePassengerUseCase;
import primer.intento.time.passenger.application.port.in.GetPassengerUseCase;
import primer.intento.time.passenger.domain.model.Passenger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {

    private final CreatePassengerUseCase createPassengerUseCase;
    private final GetPassengerUseCase getPassengerUseCase;

    public PassengerController(CreatePassengerUseCase createPassengerUseCase,
                               GetPassengerUseCase getPassengerUseCase) {
        this.createPassengerUseCase = createPassengerUseCase;
        this.getPassengerUseCase = getPassengerUseCase;
    }

    @PostMapping
    public ResponseEntity<PassengerResponseDto> create(@Valid @RequestBody PassengerRequestDto dto) {
        CreatePassengerCommand command = new CreatePassengerCommand(
                dto.firstName(),
                dto.lastName(),
                dto.email(),
                dto.documentNumber()
        );
        Passenger created = createPassengerUseCase.createPassenger(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(PassengerResponseDto.fromDomain(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassengerResponseDto> getById(@PathVariable Long id) {
        Passenger passenger = getPassengerUseCase.getById(id);
        return ResponseEntity.ok(PassengerResponseDto.fromDomain(passenger));
    }

    @GetMapping
    public ResponseEntity<List<PassengerResponseDto>> getAll() {
        List<PassengerResponseDto> list = getPassengerUseCase.getAll().stream()
                .map(PassengerResponseDto::fromDomain)
                .toList();
        return ResponseEntity.ok(list);
    }
}