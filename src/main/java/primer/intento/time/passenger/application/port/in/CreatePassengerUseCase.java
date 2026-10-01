package primer.intento.time.passenger.application.port.in;

import primer.intento.time.passenger.domain.model.Passenger;

public interface CreatePassengerUseCase {
    Passenger createPassenger(CreatePassengerCommand command);
}