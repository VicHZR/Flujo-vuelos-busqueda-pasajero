package primer.intento.time.booking.domain.model;

import java.time.LocalDate;

public class LodgingReservation {
    private Long id;
    private String accommodationType;
    private String hotelName;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private Passenger passenger;
    private FlightTicket flightTicket;

    private LodgingReservation(Builder builder) {
        this.id = builder.id;
        this.accommodationType = builder.accommodationType;
        this.hotelName = builder.hotelName;
        this.roomType = builder.roomType;
        this.checkInDate = builder.checkInDate;
        this.checkOutDate = builder.checkOutDate;
        this.passenger = builder.passenger;
        this.flightTicket = builder.flightTicket;
    }

    public LodgingReservation() {}

    public LodgingReservation(Long id, String accommodationType, String hotelName, String roomType, LocalDate checkInDate, LocalDate checkOutDate, Passenger passenger, FlightTicket flightTicket) {
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
    public String getAccommodationType() { return accommodationType; }
    public String getHotelName() { return hotelName; }
    public String getRoomType() { return roomType; }
    public LocalDate getCheckInDate() { return checkInDate; }
    public LocalDate getCheckOutDate() { return checkOutDate; }
    public Passenger getPassenger() { return passenger; }
    public FlightTicket getFlightTicket() { return flightTicket; }

    public void setId(Long id) { this.id = id; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }
    public void setHotelName(String hotelName) { this.hotelName = hotelName; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }
    public void setFlightTicket(FlightTicket flightTicket) { this.flightTicket = flightTicket; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        private String accommodationType;
        private String hotelName;
        private String roomType;
        private LocalDate checkInDate;
        private LocalDate checkOutDate;
        private Passenger passenger;
        private FlightTicket flightTicket;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder accommodationType(String accommodationType) {
            this.accommodationType = accommodationType;
            return this;
        }

        public Builder hotelName(String hotelName) {
            this.hotelName = hotelName;
            return this;
        }

        public Builder roomType(String roomType) {
            this.roomType = roomType;
            return this;
        }

        public Builder checkInDate(LocalDate checkInDate) {
            this.checkInDate = checkInDate;
            return this;
        }

        public Builder checkOutDate(LocalDate checkOutDate) {
            this.checkOutDate = checkOutDate;
            return this;
        }

        public Builder passenger(Passenger passenger) {
            this.passenger = passenger;
            return this;
        }

        public Builder flightTicket(FlightTicket flightTicket) {
            this.flightTicket = flightTicket;
            return this;
        }

        public LodgingReservation build() {
            return new LodgingReservation(this);
        }
    }
}