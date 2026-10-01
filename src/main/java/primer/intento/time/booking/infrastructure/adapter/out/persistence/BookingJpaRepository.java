package primer.intento.time.booking.infrastructure.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingJpaRepository extends JpaRepository<BookingEntity, Long> {

    // Derived Query Method (Clase 1)
    List<BookingEntity> findByPassengerEmail(String email);

    // Consulta JPQL con JOIN entre Booking, Passenger y Trip (Clase 1)
    @Query("SELECT b FROM BookingEntity b JOIN b.passenger p JOIN b.trip t WHERE p.id = :passengerId")
    List<BookingEntity> findAllBookingsByPassengerId(@Param("passengerId") Long passengerId);
}