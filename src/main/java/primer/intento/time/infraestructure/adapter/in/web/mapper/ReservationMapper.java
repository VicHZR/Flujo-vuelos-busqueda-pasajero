package primer.intento.time.infraestructure.adapter.in.web.mapper;

import primer.intento.time.domain.model.FlightTicket;
import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.domain.model.Passenger;
import primer.intento.time.infraestructure.adapter.in.web.dto.ReservationRequestDto;
import primer.intento.time.infraestructure.adapter.in.web.dto.ReservationResponseDto;

public class ReservationMapper {

    public static LodgingReservation toDomain(ReservationRequestDto dto) {
        if (dto == null) return null;

        // Mapeo del Pasajero (usando el constructor: id, firstName, lastName, documentType, documentNumber, age)
        Passenger passenger = null;
        if (dto.getPassenger() != null) {
            passenger = new Passenger(
                    null, // id se genera en la base de datos
                    dto.getPassenger().getFirstName(),
                    dto.getPassenger().getLastName(),
                    dto.getPassenger().getDocumentType(),
                    dto.getPassenger().getDocumentNumber(),
                    dto.getPassenger().getAge()
            );
        }

        // Mapeo del Ticket de Vuelo
        FlightTicket flightTicket = null;
        if (dto.getFlightTicket() != null) {
            flightTicket = new FlightTicket(
                    null,
                    dto.getFlightTicket().getTicketNumber(),
                    dto.getFlightTicket().getAirline(),
                    dto.getFlightTicket().getSeatNumber(),
                    dto.getFlightTicket().getFlightNumber(),
                    dto.getFlightTicket().getFlightTime(),
                    passenger
            );
        }

        // Mapeo de la Reserva principal usando su Builder
        return LodgingReservation.builder()
                .accommodationType(dto.getAccommodationType())
                .passenger(passenger)
                .flightTicket(flightTicket)
                .build();
    }

    public static ReservationResponseDto toResponseDto(LodgingReservation domain) {
        if (domain == null) return null;
        return new ReservationResponseDto(domain.getId(), domain.getAccommodationType());
    }
}