package primer.intento.time.booking.application.port.in;

public record CreateBookingCommand(
        Long passengerId,
        Long tripId
) {}