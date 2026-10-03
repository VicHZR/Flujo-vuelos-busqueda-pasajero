package primer.intento.time.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight_tickets")
public class FlightTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ticketNumber;
    private String airline;
    private String seatNumber;
    private String flightNumber;
    private LocalDateTime flightTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passenger;

    // Generar constructores vacíos, getters y setters en tu IDE...
    public FlightTicketEntity() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
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
    public PassengerEntity getPassenger() { return passenger; }
    public void setPassenger(PassengerEntity passenger) { this.passenger = passenger; }
}
