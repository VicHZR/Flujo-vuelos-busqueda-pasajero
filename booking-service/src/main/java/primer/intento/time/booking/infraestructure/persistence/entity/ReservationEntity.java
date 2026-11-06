package primer.intento.time.booking.infraestructure.persistence.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "lodging_reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_type", nullable = false)
    private String accommodationType;

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @Column(name = "room_type", nullable = false)
    private String roomType; // <-- Columna mapeada para la base de datos

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "passenger_id")
    private PassengerEntity passenger;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "flight_ticket_id")
    private FlightTicketEntity flightTicket;

    public ReservationEntity() {}

    public ReservationEntity(Long id, String accommodationType, String hotelName, String roomType, LocalDate checkInDate, LocalDate checkOutDate, PassengerEntity passenger, FlightTicketEntity flightTicket) {
        this.id = id;
        this.accommodationType = accommodationType;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAccommodationType() { return accommodationType; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }

    public String getHotelName() { return hotelName; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }

    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }

    public PassengerEntity getPassenger() { return passenger; }
    public void setPassenger(PassengerEntity passenger) { this.passenger = passenger; }

    public FlightTicketEntity getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketEntity flightTicket) { this.flightTicket = flightTicket; }
}