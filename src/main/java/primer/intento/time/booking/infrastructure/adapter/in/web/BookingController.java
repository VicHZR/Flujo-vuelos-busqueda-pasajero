package primer.intento.time.booking.infrastructure.adapter.in.web;

import primer.intento.time.booking.application.port.in.CreateBookingCommand;
import primer.intento.time.booking.application.port.in.CreateBookingUseCase;
import primer.intento.time.booking.domain.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/bookings")
public class BookingController {

    private final CreateBookingUseCase createBookingUseCase;

    public BookingController(CreateBookingUseCase createBookingUseCase) {
        this.createBookingUseCase = createBookingUseCase;
    }

    @PostMapping
    public ResponseEntity<Booking> create(@RequestBody BookingRequestDto dto) {
        CreateBookingCommand command = new CreateBookingCommand(dto.passengerId(), dto.tripId());
        Booking created = createBookingUseCase.createBooking(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}

record BookingRequestDto(Long passengerId, Long tripId) {}