package primer.intento.time.booking.application.port.out;

import primer.intento.time.booking.domain.model.Booking;

import java.util.List;
import java.util.Optional;

public interface BookingRepositoryPort {
    Booking save(Booking booking);
    Optional<Booking> findById(Long id);
    List<Booking> findByPassengerEmail(String email);
    List<Booking> findAllBookingsByPassengerId(Long passengerId);
}