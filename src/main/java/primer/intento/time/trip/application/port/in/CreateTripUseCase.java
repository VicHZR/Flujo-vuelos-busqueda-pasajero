package primer.intento.time.trip.application.port.in;

import primer.intento.time.trip.domain.model.Trip;

public interface CreateTripUseCase {
    Trip createTrip(CreateTripCommand command);
}