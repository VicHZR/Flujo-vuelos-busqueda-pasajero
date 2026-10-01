package primer.intento.time.booking.domain.model;

import java.time.LocalDateTime;

public class Booking {
    private Long id;
    private Long passengerId;
    private Long tripId;
    private LocalDateTime bookingDate;
    private String status;

    public Booking(Long id, Long passengerId, Long tripId, LocalDateTime bookingDate, String status) {
        this.id = id;
        this.passengerId = passengerId;
        this.tripId = tripId;
        this.bookingDate = bookingDate;
        this.status = status;
    }

    public Long getId() { return id; }
    public Long getPassengerId() { return passengerId; }
    public Long getTripId() { return tripId; }
    public LocalDateTime getBookingDate() { return bookingDate; }
    public String getStatus() { return status; }
}