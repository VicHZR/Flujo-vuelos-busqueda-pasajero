package primer.intento.time.booking.application.port.out;

public interface FlightValidationPort {
    boolean validateFlightExists(String flightNumber);
}