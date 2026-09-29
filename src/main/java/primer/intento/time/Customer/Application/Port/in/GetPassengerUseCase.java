package primer.intento.time.Customer.Application.Port.in;

import primer.intento.time.Customer.Domain.Model.Passenger;
import java.util.List;

public interface GetPassengerUseCase {
    Passenger findById (Long id);
    List<Passenger> findAll();
}
