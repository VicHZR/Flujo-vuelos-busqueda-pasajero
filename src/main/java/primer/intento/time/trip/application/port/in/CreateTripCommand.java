package primer.intento.time.trip.application.port.in;

import java.math.BigDecimal;

public record CreateTripCommand(
        String origin,
        String destination,
        BigDecimal price
) {}