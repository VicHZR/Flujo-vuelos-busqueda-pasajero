package primer.intento.time.booking.application.service;

import org.springframework.stereotype.Service;
import primer.intento.time.booking.application.port.in.ManageReservationUseCase;
import primer.intento.time.booking.application.port.out.FlightValidationPort;
import primer.intento.time.booking.application.port.out.ReservationRepositoryPort;
import primer.intento.time.booking.domain.exception.ResourceNotFoundException;
import primer.intento.time.booking.domain.model.LodgingReservation;

import java.util.List;

@Service
public class ReservationService implements ManageReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;
    private final FlightValidationPort flightValidationPort;

    public ReservationService(ReservationRepositoryPort reservationRepositoryPort,
                              FlightValidationPort flightValidationPort) {
        this.reservationRepositoryPort = reservationRepositoryPort;
        this.flightValidationPort = flightValidationPort;
    }

    @Override
    public LodgingReservation createReservation(LodgingReservation reservation) {
        // Validar si la reserva incluye un tiquete de vuelo y si este existe en el flight-service
        if (reservation.getFlightTicket() != null && reservation.getFlightTicket().getFlightNumber() != null) {
            String flightNumber = reservation.getFlightTicket().getFlightNumber();
            boolean flightExists = flightValidationPort.validateFlightExists(flightNumber);

            if (!flightExists) {
                throw new ResourceNotFoundException("El vuelo con número " + flightNumber + " no existe o no está disponible.");
            }
        }

        return reservationRepositoryPort.save(reservation);
    }

    @Override
    public LodgingReservation getReservation(Long id) {
        return reservationRepositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation not found with id: " + id));
    }

    @Override
    public List<LodgingReservation> getAllReservations() {
        return reservationRepositoryPort.findAll();
    }

    @Override
    public LodgingReservation updateReservation(Long id, LodgingReservation reservation) {
        getReservation(id); // Valida que exista
        return reservationRepositoryPort.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        getReservation(id); // Valida que exista
        reservationRepositoryPort.deleteById(id);
    }
}