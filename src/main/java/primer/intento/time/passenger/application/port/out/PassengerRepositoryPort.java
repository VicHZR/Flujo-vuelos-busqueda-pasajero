package primer.intento.time.passenger.application.port.out;

import primer.intento.time.passenger.domain.model.Passenger;
import java.util.List;
import java.util.Optional;

public interface PassengerRepositoryPort {
    Passenger save(Passenger passenger);
    Optional<Passenger> findById(Long id);
    Optional<Passenger> findByEmail(String email);
    List<Passenger> findAll();
}