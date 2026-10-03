package primer.intento.time.application.port.in;

import primer.intento.time.domain.model.LodgingReservation;
import java.util.List;

public interface ManageReservationUseCase {
    LodgingReservation createReservation(LodgingReservation reservation);
    LodgingReservation getReservation(Long id);
    List<LodgingReservation> getAllReservations();
    LodgingReservation updateReservation(Long id, LodgingReservation reservation);
    void deleteReservation(Long id);
}