package primer.intento.time.infraestructure.adapter.in.web.dto;

public class ReservationResponseDto {
    private Long id;
    private String accommodationType;
    private PassengerDto passenger;
    private FlightTicketDto flightTicket;

    public ReservationResponseDto() {}

    public ReservationResponseDto(Long id, String accommodationType) {
        this.id = id;
        this.accommodationType = accommodationType;
    }

    public ReservationResponseDto(Long id, String accommodationType, PassengerDto passenger, FlightTicketDto flightTicket) {
        this.id = id;
        this.accommodationType = accommodationType;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }

    public PassengerDto getPassenger() { return passenger; }
    public void setPassenger(PassengerDto passenger) { this.passenger = passenger; }

    public FlightTicketDto getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketDto flightTicket) { this.flightTicket = flightTicket; }
}