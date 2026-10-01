package primer.intento.time.booking.application.port.in;

import primer.intento.time.booking.domain.model.Booking;

public interface CreateBookingUseCase {
    Booking createBooking(CreateBookingCommand command);
}