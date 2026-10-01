package primer.intento.time.trip.domain.model;

import java.math.BigDecimal;

public class Trip {
    private Long id;
    private String origin;
    private String destination;
    private BigDecimal price;

    public Trip(Long id, String origin, String destination, BigDecimal price) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.price = price;
    }

    public Long getId() { return id; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public BigDecimal getPrice() { return price; }
}