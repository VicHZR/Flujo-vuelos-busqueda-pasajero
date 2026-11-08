package primer.intento.time;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "primer.intento.time")
@EnableJpaRepositories(basePackages = "primer.intento.time.booking.infraestructure.persistence")
@EntityScan(basePackages = "primer.intento.time.booking.infraestructure.persistence")
@EnableFeignClients(basePackages = "primer.intento.time.booking.infraestructure.out.feign")
public class BookingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookingApplication.class, args);
    }
}