package primer.intento.time.booking.application.service;

import primer.intento.time.booking.application.port.in.CreateBookingCommand;
import primer.intento.time.booking.application.port.in.CreateBookingUseCase;
import primer.intento.time.booking.application.port.out.BookingRepositoryPort;
import primer.intento.time.booking.domain.model.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BookingService implements CreateBookingUseCase {

    private final BookingRepositoryPort bookingRepositoryPort;

    public BookingService(BookingRepositoryPort bookingRepositoryPort) {
        this.bookingRepositoryPort = bookingRepositoryPort;
    }

    @Override
    public Booking createBooking(CreateBookingCommand command) {
        Booking booking = new Booking(
                null,
                command.passengerId(),
                command.tripId(),
                LocalDateTime.now(),
                "CONFIRMED"
        );
        return bookingRepositoryPort.save(booking);
    }
}