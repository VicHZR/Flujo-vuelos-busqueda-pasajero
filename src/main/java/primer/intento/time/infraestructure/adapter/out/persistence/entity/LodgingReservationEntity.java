package primer.intento.time.infraestructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lodging_reservations")
public class LodgingReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accommodationType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passenger;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "flight_ticket_id", nullable = false)
    private FlightTicketEntity flightTicket;

    public LodgingReservationEntity() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }
    public PassengerEntity getPassenger() { return passenger; }
    public void setPassenger(PassengerEntity passenger) { this.passenger = passenger; }
    public FlightTicketEntity getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketEntity flightTicket) { this.flightTicket = flightTicket; }
}