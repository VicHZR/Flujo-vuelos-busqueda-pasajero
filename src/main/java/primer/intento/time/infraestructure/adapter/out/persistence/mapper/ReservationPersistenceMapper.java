package primer.intento.time.infraestructure.adapter.out.persistence.mapper;

import primer.intento.time.domain.model.LodgingReservation;
import primer.intento.time.infraestructure.adapter.out.persistence.entity.LodgingReservationEntity;

public class ReservationPersistenceMapper {

    public static LodgingReservationEntity toEntity(LodgingReservation domain) {
        if (domain == null) return null;
        LodgingReservationEntity entity = new LodgingReservationEntity();
        entity.setId(domain.getId());
        entity.setAccommodationType(domain.getAccommodationType());
        return entity;
    }

    public static LodgingReservation toDomain(LodgingReservationEntity entity) {
        if (entity == null) return null;
        return LodgingReservation.builder()
                .id(entity.getId())
                .accommodationType(entity.getAccommodationType())
                .build();
    }
}