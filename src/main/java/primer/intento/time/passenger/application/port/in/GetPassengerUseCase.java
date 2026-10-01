package primer.intento.time.passenger.application.port.in;

import primer.intento.time.passenger.domain.model.Passenger;
import java.util.List;

public interface GetPassengerUseCase {
    Passenger getById(Long id);
    Passenger getByEmail(String email);
    List<Passenger> getAll();
}