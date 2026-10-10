package primer.intento.time.application.service;

import primer.intento.time.application.port.in.ManageReservationUseCase;
import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.domain.model.LodgingReservation;

import primer.intento.time.domain.exception.ResourceNotFoundException;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

public class ReservationService implements ManageReservationUseCase {

    private final ReservationRepositoryPort repositoryPort;

    public ReservationService(ReservationRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    @Transactional
    public LodgingReservation createReservation(LodgingReservation reservation) {
        return repositoryPort.save(reservation);
    }

    @Override
    @Transactional(readOnly = true)
    public LodgingReservation getReservation(Long id) {
        return repositoryPort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Reservation with ID " + id + " not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<LodgingReservation> getAllReservations() {
        return repositoryPort.findAll();
    }

    @Override
    @Transactional
    public LodgingReservation updateReservation(Long id, LodgingReservation reservation) {
        getReservation(id);
        return repositoryPort.save(reservation);
    }

    @Override
    @Transactional // Asegura atomicidad en la eliminación
    public void deleteReservation(Long id) {
        getReservation(id);
        repositoryPort.deleteById(id);
    }
}