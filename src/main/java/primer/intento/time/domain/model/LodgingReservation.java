package primer.intento.time.domain.model;

public class LodgingReservation {
    private Long id;
    private String accommodationType;
    private Passenger passenger;
    private FlightTicket flightTicket;

    public LodgingReservation(Long id, String accommodationType, Passenger passenger, FlightTicket flightTicket) {
        this.id = id;
        this.accommodationType = accommodationType;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    public Long getId() { return id; }
    public String getAccommodationType() { return accommodationType; }
    public Passenger getPassenger() { return passenger; }
    public FlightTicket getFlightTicket() { return flightTicket; }
}