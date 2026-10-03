package primer.intento.time.infraestructure.adapter.out.persistence.repository;


import primer.intento.time.infraestructure.adapter.out.persistence.entity.LodgingReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataReservationRepository extends JpaRepository<LodgingReservationEntity, Long> {
}