package primer.intento.time.booking.infraestructure.persistence.repository;

import primer.intento.time.booking.infraestructure.persistence.entity.ReservationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataReservationRepository extends JpaRepository<ReservationEntity, Long> {
}