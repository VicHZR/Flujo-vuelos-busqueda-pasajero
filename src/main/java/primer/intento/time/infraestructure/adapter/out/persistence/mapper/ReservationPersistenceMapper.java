package primer.intento.time.infraestructure.adapter.out.persistence.mapper;

import primer.intento.time.domain.model.FlightTicket;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.domain.model.Passenger;
import primer.intento.time.infraestructure.adapter.out.persistence.entity.FlightTicketEntity;
import primer.intento.time.infraestructure.adapter.out.persistence.entity.LodgingReservationEntity;
import primer.intento.time.infraestructure.adapter.out.persistence.entity.PassengerEntity;

public class ReservationPersistenceMapper {

    public static LodgingReservationEntity toEntity(LodgingReservation domain) {
        if (domain == null) return null;

        LodgingReservationEntity entity = new LodgingReservationEntity();
        entity.setId(domain.getId());
        entity.setAccommodationType(domain.getAccommodationType());

        // 1. Mapear Pasajero de Dominio a Entidad JPA
        PassengerEntity passengerEntity = null;
        if (domain.getPassenger() != null) {
            passengerEntity = new PassengerEntity();
            passengerEntity.setId(domain.getPassenger().getId());
            passengerEntity.setFirstName(domain.getPassenger().getFirstName());
            passengerEntity.setLastName(domain.getPassenger().getLastName());
            passengerEntity.setDocumentType(domain.getPassenger().getDocumentType());
            passengerEntity.setDocumentNumber(domain.getPassenger().getDocumentNumber());
            passengerEntity.setAge(domain.getPassenger().getAge());
            entity.setPassenger(passengerEntity);
        }

        // 2. Mapear Ticket de Vuelo de Dominio a Entidad JPA
        if (domain.getFlightTicket() != null) {
            FlightTicketEntity flightTicketEntity = new FlightTicketEntity();
            flightTicketEntity.setId(domain.getFlightTicket().getId());
            flightTicketEntity.setTicketNumber(domain.getFlightTicket().getTicketNumber());
            flightTicketEntity.setAirline(domain.getFlightTicket().getAirline());
            flightTicketEntity.setSeatNumber(domain.getFlightTicket().getSeatNumber());
            flightTicketEntity.setFlightNumber(domain.getFlightTicket().getFlightNumber());
            flightTicketEntity.setFlightTime(domain.getFlightTicket().getFlightTime());

            // Vincular el pasajero al vuelo para cumplir con la restricción NOT NULL de MySQL
            flightTicketEntity.setPassenger(passengerEntity);

            entity.setFlightTicket(flightTicketEntity);
        }

        return entity;
    }

    public static LodgingReservation toDomain(LodgingReservationEntity entity) {
        if (entity == null) return null;

        // 1. Mapear Pasajero de Entidad JPA a Dominio
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

        // 2. Mapear Ticket de Vuelo de Entidad JPA a Dominio
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
                .passenger(passenger)
                .flightTicket(flightTicket)
                .build();
    }
}