package primer.intento.time.booking.infraestructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "lodging_reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_type", nullable = false)
    private String accommodationType;

    @Column(name = "hotel_name", nullable = false) // <-- AÑADIDO PARA LA COLUMNA SQL
    private String hotelName; // <-- AÑADIDO

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passenger_id")
    private PassengerEntity passenger;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "flight_ticket_id")
    private FlightTicketEntity flightTicket;

    public ReservationEntity() {}

    public ReservationEntity(Long id, String accommodationType, String hotelName, PassengerEntity passenger, FlightTicketEntity flightTicket) {
        this.id = id;
        this.accommodationType = accommodationType;
        this.hotelName = hotelName;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public PassengerEntity getPassenger() { return passenger; }
    public void setPassenger(PassengerEntity passenger) { this.passenger = passenger; }

    public FlightTicketEntity getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketEntity flightTicket) { this.flightTicket = flightTicket; }
}