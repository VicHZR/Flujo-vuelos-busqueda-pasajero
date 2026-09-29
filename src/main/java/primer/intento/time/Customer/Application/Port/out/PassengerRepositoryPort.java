package primer.intento.time.Customer.Application.Port.out;

import primer.intento.time.Customer.Domain.Model.Passenger;

import java.util.List;

public interface PassengerRepositoryPort {
    Passenger save (Passenger passenger);
    List <Passenger> findAll();
    Passenger findById (Long id);
}
