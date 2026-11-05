package primer.intento.time.booking.infraestructure.web.dto;

import jakarta.validation.constraints.NotBlank;

public class ReservationRequestDto {

    @NotBlank(message = "El tipo de alojamiento no puede estar vacío")
    private String accommodationType;

    @NotBlank(message = "El nombre del hotel no puede estar vacío")
    private String hotelName; // <-- AÑADIDO

    private PassengerDto passenger;
    private FlightTicketDto flightTicket;

    public ReservationRequestDto() {}

    public ReservationRequestDto(String accommodationType, String hotelName, PassengerDto passenger, FlightTicketDto flightTicket) {
        this.accommodationType = accommodationType;
        this.hotelName = hotelName;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }

    public String getHotelName() { return hotelName; } // <-- AÑADIDO
    public void setHotelName(String hotelName) { this.hotelName = hotelName; } // <-- AÑADIDO

    public PassengerDto getPassenger() { return passenger; }
    public void setPassenger(PassengerDto passenger) { this.passenger = passenger; }

    public FlightTicketDto getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketDto flightTicket) { this.flightTicket = flightTicket; }
}