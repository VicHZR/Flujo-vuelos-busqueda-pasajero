package primer.intento.time.infraestructure.adapter.out.persistence;

import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.infraestructure.adapter.out.persistence.entity.*;
import primer.intento.time.infraestructure.adapter.out.persistence.repository.SpringDataReservationRepository;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ReservationJpaAdapter implements ReservationRepositoryPort {

    private final SpringDataReservationRepository repository;

    public ReservationJpaAdapter(SpringDataReservationRepository repository) {
        this.repository = repository;
    }

    @Override
    public LodgingReservation save(LodgingReservation reservation) {
        LodgingReservationEntity entity = new LodgingReservationEntity();

        PassengerEntity passEntity = new PassengerEntity();
        passEntity.setFirstName(reservation.getPassenger().getFirstName());
        passEntity.setLastName(reservation.getPassenger().getLastName());
        passEntity.setDocumentType(reservation.getPassenger().getDocumentType());
        passEntity.setDocumentNumber(reservation.getPassenger().getDocumentNumber());
        passEntity.setAge(reservation.getPassenger().getAge());

        FlightTicketEntity flightEntity = new FlightTicketEntity();
        flightEntity.setTicketNumber(reservation.getFlightTicket().getTicketNumber());
        flightEntity.setAirline(reservation.getFlightTicket().getAirline());
        flightEntity.setSeatNumber(reservation.getFlightTicket().getSeatNumber());
        flightEntity.setFlightNumber(reservation.getFlightTicket().getFlightNumber());
        flightEntity.setFlightTime(reservation.getFlightTicket().getFlightTime());
        flightEntity.setPassenger(passEntity);

        entity.setAccommodationType(reservation.getAccommodationType());
        entity.setPassenger(passEntity);
        entity.setFlightTicket(flightEntity);

        repository.save(entity);
        return reservation;
    }

    @Override
    public Optional<LodgingReservation> findById(Long id) {
        return repository.findById(id).map(entity -> new LodgingReservation(
                entity.getId(), entity.getAccommodationType(), null, null
        ));
    }

    @Override
    public List<LodgingReservation> findAll() {
        return repository.findAll().stream().map(entity -> new LodgingReservation(
                entity.getId(), entity.getAccommodationType(), null, null
        )).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}