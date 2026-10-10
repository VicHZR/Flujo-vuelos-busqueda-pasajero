package primer.intento.time.domain.model;

public class LodgingReservation {
    private Long id;
    private String accommodationType;
    private Passenger passenger;
    private FlightTicket flightTicket;

    // Constructor privado que recibe el Builder
    private LodgingReservation(Builder builder) {
        this.id = builder.id;
        this.accommodationType = builder.accommodationType;
        this.passenger = builder.passenger;
        this.flightTicket = builder.flightTicket;
    }

    // Constructor vacío
    public LodgingReservation() {}

    // Constructor tradicional (lo puedes conservar si ya lo usabas)
    public LodgingReservation(Long id, String accommodationType, Passenger passenger, FlightTicket flightTicket) {
        this.id = id;
        this.accommodationType = accommodationType;
        this.passenger = passenger;
        this.flightTicket = flightTicket;
    }

    // Getters
    public Long getId() { return id; }
    public String getAccommodationType() { return accommodationType; }
    public Passenger getPassenger() { return passenger; }
    public FlightTicket getFlightTicket() { return flightTicket; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setAccommodationType(String accommodationType) { this.accommodationType = accommodationType; }
    public void setPassenger(Passenger passenger) { this.passenger = passenger; }
    public void setFlightTicket(FlightTicket flightTicket) { this.flightTicket = flightTicket; }


    // Metodo estatico para iniciar el builder

    public static Builder builder() {
        return new Builder();
    }

    // Clase Builder estática interna
    public static class Builder {
        private Long id;
        private String accommodationType;
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