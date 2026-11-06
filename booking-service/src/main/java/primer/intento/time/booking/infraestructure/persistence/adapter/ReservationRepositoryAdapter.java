package primer.intento.time.booking.infraestructure.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import primer.intento.time.booking.application.port.out.ReservationRepositoryPort;
import primer.intento.time.booking.domain.model.FlightTicket;
import primer.intento.time.booking.domain.model.LodgingReservation;
import primer.intento.time.booking.domain.model.Passenger;
import primer.intento.time.booking.infraestructure.persistence.entity.FlightTicketEntity;
import primer.intento.time.booking.infraestructure.persistence.entity.PassengerEntity;
import primer.intento.time.booking.infraestructure.persistence.entity.ReservationEntity;
import primer.intento.time.booking.infraestructure.persistence.repository.SpringDataReservationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Primary
@Component
@RequiredArgsConstructor
public class ReservationRepositoryAdapter implements ReservationRepositoryPort {

    private final SpringDataReservationRepository repository;

    @Override
    public LodgingReservation save(LodgingReservation reservation) {
        ReservationEntity entity = toEntity(reservation);
        ReservationEntity savedEntity = repository.save(entity);
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

    private ReservationEntity toEntity(LodgingReservation domain) {
        if (domain == null) return null;

        PassengerEntity passengerEntity = null;
        if (domain.getPassenger() != null) {
            passengerEntity = new PassengerEntity(
                    domain.getPassenger().getId(),
                    domain.getPassenger().getFirstName(),
                    domain.getPassenger().getLastName(),
                    domain.getPassenger().getDocumentType(),
                    domain.getPassenger().getDocumentNumber(),
                    domain.getPassenger().getAge()
            );
        }

        FlightTicketEntity flightTicketEntity = null;
        if (domain.getFlightTicket() != null) {
            flightTicketEntity = new FlightTicketEntity(
                    domain.getFlightTicket().getId(),
                    domain.getFlightTicket().getTicketNumber(),
                    domain.getFlightTicket().getAirline(),
                    domain.getFlightTicket().getSeatNumber(),
                    domain.getFlightTicket().getFlightNumber(),
                    domain.getFlightTicket().getFlightTime(),
                    passengerEntity
            );
        }

        ReservationEntity entity = new ReservationEntity();
        entity.setId(domain.getId());
        entity.setAccommodationType(domain.getAccommodationType());
        entity.setHotelName(domain.getHotelName());
        entity.setRoomType(domain.getRoomType());
        entity.setCheckInDate(domain.getCheckInDate());
        entity.setCheckOutDate(domain.getCheckOutDate());
        entity.setPassenger(passengerEntity);
        entity.setFlightTicket(flightTicketEntity);

        return entity;
    }

    private LodgingReservation toDomain(ReservationEntity entity) {
        if (entity == null) return null;

        Passenger passenger = null;
        if (entity.getPassenger() != null) {
            passenger = new Passenger(
                    entity.getPassenger().getId(),
                    entity.getPassenger().getFirstName(),
                    entity.getPassenger().getLastName(),
                    entity.getPassenger().getDocumentType(),
                    entity.getPassenger().getDocumentNumber(),
                    entity.getPassenger().getAge()
            );
        }

        FlightTicket flightTicket = null;
        if (entity.getFlightTicket() != null) {
            flightTicket = new FlightTicket(
                    entity.getFlightTicket().getId(),
                    entity.getFlightTicket().getTicketNumber(),
                    entity.getFlightTicket().getAirline(),
                    entity.getFlightTicket().getSeatNumber(),
                    entity.getFlightTicket().getFlightNumber(),
                    entity.getFlightTicket().getFlightTime(),
                    passenger
            );
        }

        return LodgingReservation.builder()
                .id(entity.getId())
                .accommodationType(entity.getAccommodationType())
                .hotelName(entity.getHotelName())
                .roomType(entity.getRoomType())
                .checkInDate(entity.getCheckInDate())
                .checkOutDate(entity.getCheckOutDate())
                .passenger(passenger)
                .flightTicket(flightTicket)
                .build();
    }
}