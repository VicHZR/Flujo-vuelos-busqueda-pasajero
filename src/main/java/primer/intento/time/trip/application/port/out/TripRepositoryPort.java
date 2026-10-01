package primer.intento.time.trip.application.port.out;

import primer.intento.time.trip.domain.model.Trip;
import java.util.Optional;
import java.util.List;

public interface TripRepositoryPort {
    Trip save(Trip trip);
    Optional<Trip> findById(Long id);
    List<Trip> findAll();
}