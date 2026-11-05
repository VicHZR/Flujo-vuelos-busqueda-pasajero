package primer.intento.time.booking.infraestructure.persistence.adapter;

import primer.intento.time.booking.application.port.out.ReservationRepositoryPort;
import primer.intento.time.booking.domain.model.LodgingReservation;
import primer.intento.time.booking.infraestructure.persistence.entity.ReservationEntity;
import primer.intento.time.booking.infraestructure.persistence.mapper.ReservationPersistenceMapper;
import primer.intento.time.booking.infraestructure.persistence.repository.SpringDataReservationRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReservationSqlAdapter implements ReservationRepositoryPort {

    private final SpringDataReservationRepository repository;

    public ReservationSqlAdapter(SpringDataReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public LodgingReservation save(LodgingReservation reservation) {
        ReservationEntity entity = ReservationPersistenceMapper.toEntity(reservation);
        ReservationEntity savedEntity = repository.save(entity);
        return ReservationPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<LodgingReservation> findById(Long id) {
        return repository.findById(id)
                .map(ReservationPersistenceMapper::toDomain);
    }

    @Override
    public List<LodgingReservation> findAll() {
        return repository.findAll().stream()
                .map(ReservationPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}