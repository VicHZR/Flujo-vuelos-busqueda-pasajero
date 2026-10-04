package primer.intento.time.domain.model;

import java.time.LocalDateTime;

public class FlightTicket {
    private Long id;
    private String ticketNumber;
    private String airline;
    private String seatNumber;
    private String flightNumber;
    private LocalDateTime flightTime;
    private Passenger passenger;

    public FlightTicket(Long id, String ticketNumber, String airline, String seatNumber, String flightNumber, LocalDateTime flightTime, Passenger passenger) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.airline = airline;
        this.seatNumber = seatNumber;
        this.flightNumber = flightNumber;
        this.flightTime = flightTime;
        this.passenger = passenger;
    }

    public FlightTicket(){}

    public Long getId() { return id; }
    public String getTicketNumber() { return ticketNumber; }
    public String getAirline() { return airline; }
    public String getSeatNumber() { return seatNumber; }
    public String getFlightNumber() { return flightNumber; }
    public LocalDateTime getFlightTime() { return flightTime; }
    public Passenger getPassenger() { return passenger; }

    public void setId(Long id) {this.id = id;}
    public void setTicketNumber(String ticketNumber) {this.ticketNumber = ticketNumber;}
    public void setAirline(String airline) {this.airline = airline;}
    public void setSeatNumber(String seatNumber) {this.seatNumber = seatNumber;}
    public void setFlightNumber(String flightNumber) {this.flightNumber = flightNumber;}
    public void setFlightTime(LocalDateTime flightTime) {this.flightTime = flightTime;}
    public void setPassenger(Passenger passenger) {this.passenger = passenger;}
}