package primer.intento.time.booking.infraestructure.persistence.repository;

import primer.intento.time.booking.infraestructure.persistence.entity.PassengerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataPassengerRepository extends JpaRepository<PassengerEntity, Long> {

}