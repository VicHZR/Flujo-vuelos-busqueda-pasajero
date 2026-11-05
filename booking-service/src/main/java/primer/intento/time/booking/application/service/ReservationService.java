package primer.intento.time.booking.application.service;

import org.springframework.stereotype.Service;
import primer.intento.time.booking.application.port.in.ManageReservationUseCase;
import primer.intento.time.booking.application.port.out.ReservationRepositoryPort;
import primer.intento.time.booking.domain.exception.ResourceNotFoundException;
import primer.intento.time.booking.domain.model.LodgingReservation;

import java.util.List;

@Service
public class ReservationService implements ManageReservationUseCase {

    private final ReservationRepositoryPort reservationRepositoryPort;

    public ReservationService(ReservationRepositoryPort reservationRepositoryPort) {
        this.reservationRepositoryPort = reservationRepositoryPort;
    }

    @Override
    public LodgingReservation createReservation(LodgingReservation reservation) {
        // Aquí llama al método save del puerto (que a su vez usa el adaptador de persistencia)
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
        // Asume que tu modelo tiene un método para fijar el ID o manejas la actualización
        return reservationRepositoryPort.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        getReservation(id); // Valida que exista
        reservationRepositoryPort.deleteById(id);
    }
}