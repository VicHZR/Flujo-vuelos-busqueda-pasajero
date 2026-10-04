package primer.intento.time.application.service;


import primer.intento.time.application.port.in.ManageReservationUseCase;
import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.domain.model.LodgingReservation;
import java.util.List;

public class ReservationService implements ManageReservationUseCase {

    private final ReservationRepositoryPort repositoryPort;

    public ReservationService(ReservationRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public LodgingReservation createReservation(LodgingReservation reservation) {
        return repositoryPort.save(reservation);
    }

    @Override
    public LodgingReservation getReservation(Long id) {
        return repositoryPort.findById(id).orElseThrow(() -> new RuntimeException("Reservation not found"));
    }

    @Override
    public List<LodgingReservation> getAllReservations() {
        return repositoryPort.findAll();
    }

    @Override
    public LodgingReservation updateReservation(Long id, LodgingReservation reservation) {

        return repositoryPort.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {
        repositoryPort.deleteById(id);
    }
}