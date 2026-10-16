package primer.intento.time.infraestructure.adapter.in.web.dto;

import java.time.LocalDateTime;

public class FlightTicketDto {
    private String ticketNumber;
    private String airline;
    private String seatNumber;
    private String flightNumber;
    private LocalDateTime flightTime;

    public FlightTicketDto() {}

    public FlightTicketDto(String ticketNumber, String airline, String seatNumber, String flightNumber, LocalDateTime flightTime) {
        this.ticketNumber = ticketNumber;
        this.airline = airline;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.flightTime = flightTime;
    }

    public String getTicketNumber() { return ticketNumber; }
    public void setTicketNumber(String ticketNumber) { this.ticketNumber = ticketNumber; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public String getSeatNumber() { return seatNumber; }
    public void setSeatNumber(String seatNumber) { this.seatNumber = seatNumber; }

    public String getFlightNumber() { return flightNumber; }
    public void setFlightNumber(String flightNumber) { this.flightNumber = flightNumber; }

    public LocalDateTime getFlightTime() { return flightTime; }
    public void setFlightTime(LocalDateTime flightTime) { this.flightTime = flightTime; }
}