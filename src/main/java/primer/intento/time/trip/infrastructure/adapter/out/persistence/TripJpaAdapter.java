package primer.intento.time.trip.infrastructure.adapter.out.persistence;

import primer.intento.time.trip.application.port.out.TripRepositoryPort;
import primer.intento.time.trip.domain.model.Trip;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TripJpaAdapter implements TripRepositoryPort {

    private final TripJpaRepository repository;

    public TripJpaAdapter(TripJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Trip save(Trip trip) {
        TripEntity entity = new TripEntity(trip.getId(), trip.getOrigin(), trip.getDestination(), trip.getPrice());
        TripEntity saved = repository.save(entity);
        return new Trip(saved.getId(), saved.getOrigin(), saved.getDestination(), saved.getPrice());
    }

    @Override
    public Optional<Trip> findById(Long id) {
        return repository.findById(id)
                .map(e -> new Trip(e.getId(), e.getOrigin(), e.getDestination(), e.getPrice()));
    }

    @Override
    public List<Trip> findAll() {
        return repository.findAll().stream()
                .map(e -> new Trip(e.getId(), e.getOrigin(), e.getDestination(), e.getPrice()))
                .toList();
    }
}