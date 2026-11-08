package primer.intento.time.booking.infraestructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import primer.intento.time.booking.application.port.in.ManageReservationUseCase;
import primer.intento.time.booking.application.port.out.FlightValidationPort;
import primer.intento.time.booking.application.port.out.ReservationRepositoryPort;
import primer.intento.time.booking.application.service.ReservationService;

@Configuration
public class BeanConfiguration {

    @Bean
    public ManageReservationUseCase manageReservationUseCase(
            ReservationRepositoryPort reservationRepositoryPort,
            FlightValidationPort flightValidationPort) {
        return new ReservationService(reservationRepositoryPort, flightValidationPort);
    }
}