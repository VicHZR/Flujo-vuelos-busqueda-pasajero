package primer.intento.time.passenger.infrastructure.adapter.in.web;

import primer.intento.time.passenger.domain.model.Passenger;

public record PassengerResponseDto(
        Long id,
        String fullName,
        String email,
        String documentNumber
) {
    public static PassengerResponseDto fromDomain(Passenger passenger) {
        return new PassengerResponseDto(
                passenger.getId(),
                passenger.getFirstName() + " " + passenger.getLastName(),
                passenger.getEmail(),
                passenger.getDocumentNumber()
        );
    }
}