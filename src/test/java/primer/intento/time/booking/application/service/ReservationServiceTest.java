package primer.intento.time.booking.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import primer.intento.time.booking.application.port.out.FlightValidationPort;
import primer.intento.time.booking.application.port.out.ReservationRepositoryPort;
import primer.intento.time.booking.domain.exception.ResourceNotFoundException;
import primer.intento.time.booking.domain.model.FlightTicket;
import primer.intento.time.booking.domain.model.LodgingReservation;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    private ReservationRepositoryPort reservationRepositoryPort;
    private FlightValidationPort flightValidationPort;
    private ReservationService reservationService;

    @BeforeEach
    void setUp() {
        reservationRepositoryPort = mock(ReservationRepositoryPort.class);
        flightValidationPort = mock(FlightValidationPort.class);
        reservationService = new ReservationService(reservationRepositoryPort, flightValidationPort);
    }

    @Test
    void createReservation_SuccessWhenFlightIsValid() {
        // Arrange
        FlightTicket flightTicket = new FlightTicket(1L, "TICK-123", "LATAM", "12A", "LA-404", null, null);
        LodgingReservation reservation = LodgingReservation.builder()
                .id(1L)
                .hotelName("Hotel Test")
                .flightTicket(flightTicket)
                .build();

        when(flightValidationPort.validateFlightExists("LA-404")).thenReturn(true);
        when(reservationRepositoryPort.save(any(LodgingReservation.class))).thenReturn(reservation);

        // Act
        LodgingReservation result = reservationService.createReservation(reservation);

        // Assert
        assertNotNull(result);
        assertEquals("Hotel Test", result.getHotelName());
        verify(flightValidationPort, times(1)).validateFlightExists("LA-404");
        verify(reservationRepositoryPort, times(1)).save(reservation);
    }

    @Test
    void createReservation_ThrowsExceptionWhenFlightDoesNotExist() {
        // Arrange
        FlightTicket flightTicket = new FlightTicket(1L, "TICK-999", "LATAM", "14C", "BAD-999", null, null);
        LodgingReservation reservation = LodgingReservation.builder()
                .hotelName("Hotel Test")
                .flightTicket(flightTicket)
                .build();

        when(flightValidationPort.validateFlightExists("BAD-999")).thenReturn(false);

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.createReservation(reservation);
        });

        assertTrue(exception.getMessage().contains("no existe o no está disponible"));
        verify(flightValidationPort, times(1)).validateFlightExists("BAD-999");
        verify(reservationRepositoryPort, never()).save(any());
    }

    @Test
    void getReservation_SuccessWhenExists() {
        // Arrange
        LodgingReservation reservation = LodgingReservation.builder().id(1L).hotelName("Hotel Test").build();
        when(reservationRepositoryPort.findById(1L)).thenReturn(Optional.of(reservation));

        // Act
        LodgingReservation result = reservationService.getReservation(1L);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(reservationRepositoryPort, times(1)).findById(1L);
    }

    @Test
    void getReservation_ThrowsExceptionWhenNotFound() {
        // Arrange
        when(reservationRepositoryPort.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            reservationService.getReservation(99L);
        });
        verify(reservationRepositoryPort, times(1)).findById(99L);
    }
}