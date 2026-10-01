package primer.intento.time.booking.application.service;

import primer.intento.time.booking.application.port.in.CreateBookingCommand;
import primer.intento.time.booking.application.port.out.BookingRepositoryPort;
import primer.intento.time.booking.domain.model.Booking;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    private BookingRepositoryPort bookingRepositoryPort;
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        // Creamos el mock del Puerto de Salida
        bookingRepositoryPort = Mockito.mock(BookingRepositoryPort.class);
        bookingService = new BookingService(bookingRepositoryPort);
    }

    @Test
    void shouldCreateBookingSuccessfully() {
        // Arrange
        CreateBookingCommand command = new CreateBookingCommand(1L, 100L);
        Booking savedBooking = new Booking(10L, 1L, 100L, null, "CONFIRMED");

        when(bookingRepositoryPort.save(any(Booking.class))).thenReturn(savedBooking);

        // Act
        Booking result = bookingService.createBooking(command);

        // Assert
        assertNotNull(result);
        assertEquals(10L, result.getId());
        assertEquals("CONFIRMED", result.getStatus());
        verify(bookingRepositoryPort, times(1)).save(any(Booking.class));
    }
}