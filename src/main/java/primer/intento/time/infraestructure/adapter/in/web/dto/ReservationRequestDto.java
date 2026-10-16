package primer.intento.time.infraestructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public class ReservationRequestDto {

    @NotBlank(message = "El tipo de alojamiento no puede estar vacío")
    private String accommodationType;

    private PassengerDto passenger;
    private FlightTicketDto flightTicket;

    public ReservationRequestDto() {}

    public ReservationRequestDto(String accommodationType, PassengerDto passenger, FlightTicketDto flightTicket) {
        this.accommodationType = accommodationType;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }

    public PassengerDto getPassenger() { return passenger; }
    public void setPassenger(PassengerDto passenger) { this.passenger = passenger; }

    public FlightTicketDto getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketDto flightTicket) { this.flightTicket = flightTicket; }
}