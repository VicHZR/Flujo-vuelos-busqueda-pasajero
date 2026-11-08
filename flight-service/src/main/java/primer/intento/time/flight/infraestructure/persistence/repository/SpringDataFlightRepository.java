package primer.intento.time.flight.infraestructure.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import primer.intento.time.flight.infraestructure.persistence.entity.FlightEntity;

import java.util.Optional;

public interface SpringDataFlightRepository extends MongoRepository<FlightEntity, String> {

    // Método para buscar un vuelo por su número en MongoDB
    Optional<FlightEntity> findByFlightNumber(String flightNumber);

}