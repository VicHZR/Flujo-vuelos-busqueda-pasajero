package primer.intento.time.infraestructure.adapter.in.web.mapper;

import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.infraestructure.adapter.in.web.dto.ReservationRequestDto;
import primer.intento.time.infraestructure.adapter.in.web.dto.ReservationResponseDto;

public class ReservationMapper {

    public static LodgingReservation toDomain(ReservationRequestDto dto) {
        if (dto == null) return null;
        return LodgingReservation.builder()
                .accommodationType(dto.getAccommodationType())
                .build();
    }

    public static ReservationResponseDto toResponseDto(LodgingReservation domain) {
        if (domain == null) return null;
        return new ReservationResponseDto(domain.getId(), domain.getAccommodationType());
    }
}