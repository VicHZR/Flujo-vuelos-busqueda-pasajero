package primer.intento.time.booking.infraestructure.web.dto;

import java.time.LocalDate;

public class ReservationResponseDto {
    private Long id;
    private String accommodationType;
    private String hotelName;
    private String roomType;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private PassengerDto passenger;
    private FlightTicketDto flightTicket;

    public ReservationResponseDto() {}

    public ReservationResponseDto(Long id, String accommodationType, String hotelName, String roomType, LocalDate checkInDate, LocalDate checkOutDate, PassengerDto passenger, FlightTicketDto flightTicket) {
        this.id = id;
        this.accommodationType = accommodationType;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    // Getters y Setters
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

    public PassengerDto getPassenger() { return passenger; }
    public void setPassenger(PassengerDto passenger) { this.passenger = passenger; }

    public FlightTicketDto getFlightTicket() { return flightTicket; }
    public void setFlightTicket(FlightTicketDto flightTicket) { this.flightTicket = flightTicket; }
}