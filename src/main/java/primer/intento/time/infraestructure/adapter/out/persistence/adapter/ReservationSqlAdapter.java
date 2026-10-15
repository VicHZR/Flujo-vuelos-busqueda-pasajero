package primer.intento.time.infraestructure.adapter.out.persistence.adapter;

import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.infraestructure.adapter.out.persistence.entity.LodgingReservationEntity;
import primer.intento.time.infraestructure.adapter.out.persistence.mapper.ReservationPersistenceMapper;
import primer.intento.time.infraestructure.adapter.out.persistence.repository.SpringDataReservationRepository;
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
        LodgingReservationEntity entity = ReservationPersistenceMapper.toEntity(reservation);
        LodgingReservationEntity savedEntity = repository.save(entity);
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