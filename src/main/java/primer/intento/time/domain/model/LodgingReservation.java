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
    public LodgingReservation(){}

    public Long getId() { return id; }
    public String getAccommodationType() { return accommodationType; }
    public Passenger getPassenger() { return passenger; }
    public FlightTicket getFlightTicket() { return flightTicket; }

    public void setId(Long id) {this.id = id;}
    public void setAccommodationType(String accommodationType) {this.accommodationType = accommodationType;}
    public void setPassenger(Passenger passenger) {this.passenger = passenger;}
    public void setFlightTicket(FlightTicket flightTicket) {this.flightTicket = flightTicket;}
}