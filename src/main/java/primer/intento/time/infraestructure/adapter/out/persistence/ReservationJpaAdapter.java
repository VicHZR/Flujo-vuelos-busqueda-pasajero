package primer.intento.time.infraestructure.adapter.out.persistence;

import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.domain.model.FlightTicket;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.domain.model.Passenger;
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
        LodgingReservationEntity entity;

        // 1. Verificar si es una actualización (tiene ID) o una creación
        if (reservation.getId() != null) {
            entity = repository.findById(reservation.getId())
                    .orElse(new LodgingReservationEntity());
        } else {
            entity = new LodgingReservationEntity();
        }

        // 2. Actualizar tipo de alojamiento si viene en el JSON
        if (reservation.getAccommodationType() != null) {
            entity.setAccommodationType(reservation.getAccommodationType());
        }

        // 3. Actualizar pasajero de forma segura (evita el NullPointerException del PATCH)
        if (reservation.getPassenger() != null) {
            PassengerEntity passEntity = entity.getPassenger() != null ? entity.getPassenger() : new PassengerEntity();

            passEntity.setFirstName(reservation.getPassenger().getFirstName());
            passEntity.setLastName(reservation.getPassenger().getLastName());
            passEntity.setDocumentType(reservation.getPassenger().getDocumentType());
            passEntity.setDocumentNumber(reservation.getPassenger().getDocumentNumber());
            passEntity.setAge(reservation.getPassenger().getAge());

            entity.setPassenger(passEntity);
        }

        // 4. Actualizar vuelo de forma segura
        if (reservation.getFlightTicket() != null) {
            FlightTicketEntity flightEntity = entity.getFlightTicket() != null ? entity.getFlightTicket() : new FlightTicketEntity();

            flightEntity.setTicketNumber(reservation.getFlightTicket().getTicketNumber());
            flightEntity.setAirline(reservation.getFlightTicket().getAirline());
            flightEntity.setSeatNumber(reservation.getFlightTicket().getSeatNumber());
            flightEntity.setFlightNumber(reservation.getFlightTicket().getFlightNumber());
            flightEntity.setFlightTime(reservation.getFlightTicket().getFlightTime());

            // Mantenemos la relación
            if (entity.getPassenger() != null) {
                flightEntity.setPassenger(entity.getPassenger());
            }

            entity.setFlightTicket(flightEntity);
        }

        // 5. Guardar en la base de datos y retornar mapeando al dominio
        LodgingReservationEntity savedEntity = repository.save(entity);
        return toDomain(savedEntity);
    }

    @Override
    public Optional<LodgingReservation> findById(Long id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public List<LodgingReservation> findAll() {
        return repository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }


    private LodgingReservation toDomain(LodgingReservationEntity entity) {
        Passenger passenger = null;
        if (entity.getPassenger() != null) {
            passenger = new Passenger();
            passenger.setId(entity.getPassenger().getId()); // ID agregado aquí
            passenger.setFirstName(entity.getPassenger().getFirstName());
            passenger.setLastName(entity.getPassenger().getLastName());
            passenger.setDocumentType(entity.getPassenger().getDocumentType());
            passenger.setDocumentNumber(entity.getPassenger().getDocumentNumber());
            passenger.setAge(entity.getPassenger().getAge());
        }

        FlightTicket flight = null;
        if (entity.getFlightTicket() != null) {
            flight = new FlightTicket();
            flight.setId(entity.getFlightTicket().getId()); // ID agregado aquí
            flight.setTicketNumber(entity.getFlightTicket().getTicketNumber());
            flight.setAirline(entity.getFlightTicket().getAirline());
            flight.setSeatNumber(entity.getFlightTicket().getSeatNumber());
            flight.setFlightNumber(entity.getFlightTicket().getFlightNumber());
            flight.setFlightTime(entity.getFlightTicket().getFlightTime());
        }

        return new LodgingReservation(entity.getId(), entity.getAccommodationType(), passenger, flight);
    }
}