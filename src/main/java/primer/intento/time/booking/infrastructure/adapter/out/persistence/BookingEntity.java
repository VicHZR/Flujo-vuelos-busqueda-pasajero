package primer.intento.time.booking.infrastructure.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.*;
import primer.intento.time.passenger.infrastructure.adapter.out.persistence.PassengerEntity;
import primer.intento.time.trip.infrastructure.adapter.out.persistence.TripEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", nullable = false)
    private PassengerEntity passenger;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id", nullable = false)
    private TripEntity trip;

    private LocalDateTime bookingDate;
    private String status;
}