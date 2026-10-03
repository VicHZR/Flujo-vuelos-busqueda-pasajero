package primer.intento.time.infraestructure.config;

import primer.intento.time.application.port.in.ManageReservationUseCase;
import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.application.service.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public ManageReservationUseCase manageReservationUseCase(ReservationRepositoryPort port) {
        return new ReservationService(port);
    }
}