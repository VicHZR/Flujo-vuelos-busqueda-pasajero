package primer.intento.time.infraestructure.adapter.in.web.dto;

import jakarta.validation.constraints.NotBlank;

public class ReservationRequestDto {

    @NotBlank(message = "El tipo de alojamiento no puede estar vacío")
    private String accommodationType;

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }
}