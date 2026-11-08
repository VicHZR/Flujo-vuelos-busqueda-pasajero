package primer.intento.time.booking.infraestructure.out.adapter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import primer.intento.time.booking.application.port.out.FlightValidationPort;
import primer.intento.time.booking.infraestructure.out.feign.FlightClient;

@Component
public class FlightClientAdapter implements FlightValidationPort {

    private final FlightClient flightClient;

    public FlightClientAdapter(FlightClient flightClient) {
        this.flightClient = flightClient;
    }

    @Override
    public boolean validateFlightExists(String flightNumber) {
        try {
            ResponseEntity<?> response = flightClient.getFlightByNumber(flightNumber);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // Si el servicio de vuelos responde con 404 o hay un fallo de comunicación
            return false;
        }
    }
}