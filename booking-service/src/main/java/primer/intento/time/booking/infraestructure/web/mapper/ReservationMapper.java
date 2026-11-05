package primer.intento.time.booking.infraestructure.web.mapper;

import org.springframework.stereotype.Component;
import primer.intento.time.booking.domain.model.FlightTicket;
import primer.intento.time.booking.domain.model.LodgingReservation;
import primer.intento.time.booking.domain.model.Passenger;
import primer.intento.time.booking.infraestructure.web.dto.FlightTicketDto;
import primer.intento.time.booking.infraestructure.web.dto.PassengerDto;
import primer.intento.time.booking.infraestructure.web.dto.ReservationRequestDto;
import primer.intento.time.booking.infraestructure.web.dto.ReservationResponseDto;

@Component
public class ReservationMapper {

    public static LodgingReservation toDomain(ReservationRequestDto dto) {
        if (dto == null) return null;

        Passenger passenger = null;
        if (dto.getPassenger() != null) {
            passenger = new Passenger(
                    null,
                    dto.getPassenger().getFirstName(),
                    dto.getPassenger().getLastName(),
                    dto.getPassenger().getDocumentType(),
                    dto.getPassenger().getDocumentNumber(),
                    dto.getPassenger().getAge()
            );
        }

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

        return LodgingReservation.builder()
                .accommodationType(dto.getAccommodationType())
                .hotelName(dto.getHotelName())
                .passenger(passenger)
                .flightTicket(flightTicket)
                .build();
    }

    public static ReservationResponseDto toResponseDto(LodgingReservation domain) {
        if (domain == null) return null;

        PassengerDto passengerDto = null;
        if (domain.getPassenger() != null) {
            passengerDto = new PassengerDto();
            passengerDto.setFirstName(domain.getPassenger().getFirstName());
            passengerDto.setLastName(domain.getPassenger().getLastName());
            passengerDto.setDocumentType(domain.getPassenger().getDocumentType());
            passengerDto.setDocumentNumber(domain.getPassenger().getDocumentNumber());
            passengerDto.setAge(domain.getPassenger().getAge());
        }

        FlightTicketDto flightTicketDto = null;
        if (domain.getFlightTicket() != null) {
            flightTicketDto = new FlightTicketDto();
            flightTicketDto.setTicketNumber(domain.getFlightTicket().getTicketNumber());
            flightTicketDto.setAirline(domain.getFlightTicket().getAirline());
            flightTicketDto.setSeatNumber(domain.getFlightTicket().getSeatNumber());
            flightTicketDto.setFlightNumber(domain.getFlightTicket().getFlightNumber());
            flightTicketDto.setFlightTime(domain.getFlightTicket().getFlightTime());
        }

        return new ReservationResponseDto(
                domain.getId(),
                domain.getAccommodationType(),
                passengerDto,
                flightTicketDto
        );
    }
}