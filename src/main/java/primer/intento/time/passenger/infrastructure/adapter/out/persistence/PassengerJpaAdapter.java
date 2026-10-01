package primer.intento.time.passenger.infrastructure.adapter.out.persistence;

import primer.intento.time.passenger.application.port.out.PassengerRepositoryPort;
import primer.intento.time.passenger.domain.model.Passenger;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PassengerJpaAdapter implements PassengerRepositoryPort {

    private final PassengerJpaRepository repository;

    public PassengerJpaAdapter(PassengerJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Passenger save(Passenger passenger) {
        PassengerEntity entity = new PassengerEntity(
                passenger.getId(),
                passenger.getFirstName(),
                passenger.getLastName(),
                passenger.getEmail(),
                passenger.getDocumentNumber()
        );
        PassengerEntity saved = repository.save(entity);
        return mapToDomain(saved);
    }

    @Override
    public Optional<Passenger> findById(Long id) {
        return repository.findById(id).map(this::mapToDomain);
    }

    @Override
    public Optional<Passenger> findByEmail(String email) {
        return repository.findByEmail(email).map(this::mapToDomain);
    }

    @Override
    public List<Passenger> findAll() {
        return repository.findAll().stream()
                .map(this::mapToDomain)
                .toList();
    }

    private Passenger mapToDomain(PassengerEntity entity) {
        return new Passenger(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getEmail(),
                entity.getDocumentNumber()
        );
    }
}