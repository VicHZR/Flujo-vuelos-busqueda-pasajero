package primer.intento.time.booking.infrastructure.adapter.out.persistence;

import primer.intento.time.booking.application.port.out.BookingRepositoryPort;
import primer.intento.time.booking.domain.model.Booking;
import primer.intento.time.passenger.infrastructure.adapter.out.persistence.PassengerEntity;
import primer.intento.time.passenger.infrastructure.adapter.out.persistence.PassengerJpaRepository;
import primer.intento.time.trip.infrastructure.adapter.out.persistence.TripEntity;
import primer.intento.time.trip.infrastructure.adapter.out.persistence.TripJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
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
        return mapToDomain(saved);
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return bookingJpaRepository.findById(id)
                .map(this::mapToDomain);
    }

    @Override
    public List<Booking> findByPassengerEmail(String email) {
        return bookingJpaRepository.findByPassengerEmail(email).stream()
                .map(this::mapToDomain)
                .toList();
    }

    @Override
    public List<Booking> findAllBookingsByPassengerId(Long passengerId) {
        return bookingJpaRepository.findAllBookingsByPassengerId(passengerId).stream()
                .map(this::mapToDomain)
                .toList();
    }

    private Booking mapToDomain(BookingEntity entity) {
        return new Booking(
                entity.getId(),
                entity.getPassenger().getId(),
                entity.getTrip().getId(),
                entity.getBookingDate(),
                entity.getStatus()
        );
    }
}