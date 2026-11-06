package primer.intento.time.booking.infraestructure.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ReservationRequestDto {

    @NotBlank(message = "El tipo de alojamiento no puede estar vacío")
    private String accommodationType;

    @NotBlank(message = "El nombre del hotel no puede estar vacío")
    private String hotelName;

    @NotBlank(message = "El tipo de habitación no puede estar vacío")
    private String roomType;

    @NotNull(message = "La fecha de ingreso no puede estar vacía")
    private LocalDate checkInDate;

    @NotNull(message = "La fecha de salida no puede estar vacía")
    private LocalDate checkOutDate;

    private PassengerDto passenger;
    private FlightTicketDto flightTicket;

    public ReservationRequestDto() {}

    public ReservationRequestDto(String accommodationType, String hotelName, String roomType, LocalDate checkInDate, LocalDate checkOutDate, PassengerDto passenger, FlightTicketDto flightTicket) {
        this.accommodationType = accommodationType;
        this.hotelName = hotelName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

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