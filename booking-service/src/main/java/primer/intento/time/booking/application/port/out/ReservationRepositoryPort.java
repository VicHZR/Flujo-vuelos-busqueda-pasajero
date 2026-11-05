package primer.intento.time.booking.application.port.out;

import primer.intento.time.booking.domain.model.LodgingReservation;
import java.util.List;
import java.util.Optional;

public interface ReservationRepositoryPort {
    LodgingReservation save(LodgingReservation reservation);
    Optional<LodgingReservation> findById(Long id);
    List<LodgingReservation> findAll();
    void deleteById(Long id);
}