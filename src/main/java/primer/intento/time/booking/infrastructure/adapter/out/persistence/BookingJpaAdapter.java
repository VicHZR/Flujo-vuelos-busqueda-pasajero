package primer.intento.time.booking.infrastructure.adapter.out.persistence;

import primer.intento.time.booking.application.port.out.BookingRepositoryPort;
import primer.intento.time.booking.domain.model.Booking;
import primer.intento.time.passenger.infrastructure.adapter.out.persistence.PassengerEntity;
import primer.intento.time.passenger.infrastructure.adapter.out.persistence.PassengerJpaRepository;
import primer.intento.time.trip.infrastructure.adapter.out.persistence.TripEntity;
import primer.intento.time.trip.infrastructure.adapter.out.persistence.TripJpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookingJpaAdapter implements BookingRepositoryPort {

    private final BookingJpaRepository bookingJpaRepository;
    private final PassengerJpaRepository passengerJpaRepository;
    private final TripJpaRepository tripJpaRepository;

    public BookingJpaAdapter(BookingJpaRepository bookingJpaRepository,
                             PassengerJpaRepository passengerJpaRepository,
                             TripJpaRepository tripJpaRepository) {
        this.bookingJpaRepository = bookingJpaRepository;
        this.passengerJpaRepository = passengerJpaRepository;
        this.tripJpaRepository = tripJpaRepository;
    }

    @Override
    public Booking save(Booking booking) {
        PassengerEntity passenger = passengerJpaRepository.findById(booking.getPassengerId())
                .orElseThrow(() -> new IllegalArgumentException("Pasajero no encontrado"));

        TripEntity trip = tripJpaRepository.findById(booking.getTripId())
                .orElseThrow(() -> new IllegalArgumentException("Viaje no encontrado"));

        BookingEntity entity = new BookingEntity(
                booking.getId(),
                passenger,
                trip,
                booking.getBookingDate(),
                booking.getStatus()
        );

        BookingEntity saved = bookingJpaRepository.save(entity);
        return new Booking(
                saved.getId(),
                saved.getPassenger().getId(),
                saved.getTrip().getId(),
                saved.getBookingDate(),
                saved.getStatus()
        );
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingJpaRepository.findById(id)
                .map(e -> new Booking(e.getId(), e.getPassenger().getId(), e.getTrip().getId(), e.getBookingDate(), e.getStatus()));
    }
}