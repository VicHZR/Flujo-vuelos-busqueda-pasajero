package primer.intento.time.application.usecase;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import primer.intento.time.application.port.out.ReservationRepositoryPort;
import primer.intento.time.application.service.ReservationService;
import primer.intento.time.domain.model.LodgingReservation;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepositoryPort repositoryPort;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void debeRetornarReservaCuandoElIdExiste() {
        // Arrange
        Long reservationId = 1L;
        LodgingReservation mockReservation = new LodgingReservation();

        when(repositoryPort.findById(reservationId)).thenReturn(Optional.of(mockReservation));

        // Act
        LodgingReservation resultado = reservationService.getReservation(reservationId);

        // Assert
        assertNotNull(resultado);
        verify(repositoryPort, times(1)).findById(reservationId);
    }

    @Test
    void debeLanzarExcepcionCuandoReservaNoExiste() {
        // Arrange
        Long reservationId = 99L;

        when(repositoryPort.findById(reservationId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> reservationService.getReservation(reservationId)
        );

        assertEquals("Reservation not found", exception.getMessage());
        verify(repositoryPort, times(1)).findById(reservationId);
    }
}