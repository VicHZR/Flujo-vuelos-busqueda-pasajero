package primer.intento.time.passenger.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PassengerJpaRepository extends JpaRepository<PassengerEntity, Long> {
    // Derived Query Method (Clase 1)
    Optional<PassengerEntity> findByEmail(String email);
}