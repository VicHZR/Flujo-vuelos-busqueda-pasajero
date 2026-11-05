package primer.intento.time.booking.infraestructure.persistence.repository;

import primer.intento.time.booking.infraestructure.persistence.entity.FlightTicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataFlightTicketRepository extends JpaRepository<FlightTicketEntity, Long> {

}