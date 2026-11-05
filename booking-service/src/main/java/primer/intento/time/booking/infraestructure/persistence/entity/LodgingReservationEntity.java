package primer.intento.time.booking.infraestructure.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "reservations")
public class LodgingReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "accommodation_type", nullable = false)
    private String accommodationType;

    public LodgingReservationEntity() {
    }

    public LodgingReservationEntity(Long id, String accommodationType) {
        this.id = id;
        this.accommodationType = accommodationType;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }
}