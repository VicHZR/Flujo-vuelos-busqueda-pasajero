package primer.intento.time.passenger.domain.exception;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(Long id) {
        super("Pasajero no encontrado con ID: " + id);
    }

    public PassengerNotFoundException(String email) {
        super("Pasajero no encontrado con el email: " + email);
    }
}