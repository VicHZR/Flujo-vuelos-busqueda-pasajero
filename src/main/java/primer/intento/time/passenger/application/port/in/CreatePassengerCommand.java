package primer.intento.time.passenger.application.port.in;

public record CreatePassengerCommand(
        String firstName,
        String lastName,
        String email,
        String documentNumber
) {}