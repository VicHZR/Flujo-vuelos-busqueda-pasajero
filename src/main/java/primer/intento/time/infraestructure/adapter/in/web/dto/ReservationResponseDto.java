package primer.intento.time.infraestructure.adapter.in.web.dto;

public class ReservationResponseDto {
    private Long id;
    private String accommodationType;

    public ReservationResponseDto(Long id, String accommodationType) {
        this.id = id;
        this.accommodationType = accommodationType;
    }

    public Long getId() { return id; }
    public String getAccommodationType() { return accommodationType; }
}