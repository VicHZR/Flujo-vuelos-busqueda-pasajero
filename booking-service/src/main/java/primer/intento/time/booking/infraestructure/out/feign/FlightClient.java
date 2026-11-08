package primer.intento.time.booking.infraestructure.out.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "flight-service", url = "${flight.service.url:http://localhost:8595}")
public interface FlightClient {

    @GetMapping("/api/v1/flights/{flightNumber}")
    ResponseEntity<?> getFlightByNumber(@PathVariable("flightNumber") String flightNumber);
}