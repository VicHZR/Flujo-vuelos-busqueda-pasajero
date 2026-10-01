package primer.intento.time.trip.application.service;

import primer.intento.time.trip.application.port.in.CreateTripCommand;
import primer.intento.time.trip.application.port.in.CreateTripUseCase;
import primer.intento.time.trip.application.port.out.TripRepositoryPort;
import primer.intento.time.trip.domain.model.Trip;
import org.springframework.stereotype.Service;

@Service
public class TripService implements CreateTripUseCase {

    private final TripRepositoryPort tripRepositoryPort;

    public TripService(TripRepositoryPort tripRepositoryPort) {
        this.tripRepositoryPort = tripRepositoryPort;
    }

    @Override
    public Trip createTrip(CreateTripCommand command) {
        Trip trip = new Trip(
                null,
                command.origin(),
                command.destination(),
                command.price()
        );
        return tripRepositoryPort.save(trip);
    }
}