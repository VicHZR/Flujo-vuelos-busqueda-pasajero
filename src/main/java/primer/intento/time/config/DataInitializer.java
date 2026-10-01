package primer.intento.time.config;

import primer.intento.time.passenger.application.port.in.CreatePassengerCommand;
import primer.intento.time.passenger.application.port.in.CreatePassengerUseCase;
import primer.intento.time.trip.application.port.in.CreateTripCommand;
import primer.intento.time.trip.application.port.in.CreateTripUseCase;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final CreatePassengerUseCase createPassengerUseCase;
    private final CreateTripUseCase createTripUseCase;

    public DataInitializer(CreatePassengerUseCase createPassengerUseCase, CreateTripUseCase createTripUseCase) {
        this.createPassengerUseCase = createPassengerUseCase;
        this.createTripUseCase = createTripUseCase;
    }

    @Override
    public void run(String... args) {
        createPassengerUseCase.createPassenger(
                new CreatePassengerCommand("Victor", "Guzman", "victor@time.com", "77778888")
        );

        createTripUseCase.createTrip(
                new CreateTripCommand("Lima", "Cusco", new BigDecimal("150.00"))
        );
    }
}