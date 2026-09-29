package primer.intento.time.Customer.Application.Port.in;

import primer.intento.time.Customer.Domain.Model.Passenger;

public interface CreatePassengerUseCase {

    Passenger create (CreatePassengerComand cmd);
}
