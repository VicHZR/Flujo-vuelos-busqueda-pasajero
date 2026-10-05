package primer.intento.time.application.service;

import primer.intento.time.application.port.in.ManageReservationUseCase;
import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.infraestructure.adapter.in.web.exception.ResourceNotFoundException;

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
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with ID " + id + " not found"));
    }

    @Override
    public List<LodgingReservation> getAllReservations() {
        return repositoryPort.findAll();
    }

    @Override
    public LodgingReservation updateReservation(Long id, LodgingReservation reservation) {

        getReservation(id);
        return repositoryPort.save(reservation);
    }

    @Override
    public void deleteReservation(Long id) {

        getReservation(id);
        repositoryPort.deleteById(id);
    }
}