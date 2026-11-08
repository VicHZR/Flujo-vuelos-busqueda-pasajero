package primer.intento.time.application.usecase;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import primer.intento.time.booking.domain.exception.ResourceNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarPasajeroUseCaseTest {

    // Declaración de mocks para los puertos de salida o repositorios necesarios
    private PasajeroRepositoryPort pasajeroRepositoryPort;
    private BuscarPasajeroUseCase buscarPasajeroUseCase;

    @BeforeEach
    void setUp() {
        // Inicialización de mocks y del caso de uso a probar
    pasajeroRepositoryPort = mock(PasajeroRepositoryPort.class);
    buscarPasajeroUseCase = new BuscarPasajeroServiceImpl(pasajeroRepositoryPort);
    }

    @Test
    void buscarPasajero_SuccessWhenPassengerExists() {
        // Arrange (Preparación de datos y comportamientos simulados)
        String numeroDocumento = "12345678";
        Pasajero pasajeroMock = new Pasajero(1L, "Víctor", "Guzmán", numeroDocumento);
        when(pasajeroRepositoryPort.findByDocumento(numeroDocumento)).thenReturn(Optional.of(pasajeroMock));

        // Act (Ejecución del caso de uso)
        Pasajero resultado = buscarPasajeroUseCase.ejecutar(numeroDocumento);

        // Assert (Verificación de resultados y llamadas a mocks)
        assertNotNull(resultado);
        assertEquals(numeroDocumento, resultado.getDocumento());
        verify(pasajeroRepositoryPort, times(1)).findByDocumento(numeroDocumento);

        assertTrue(true, "Prueba unitaria base para BuscarPasajeroUseCase configurada correctamente.");
    }

    @Test
    void buscarPasajero_ThrowsExceptionWhenNotFound() {
        // Arrange
        String numeroDocumento = "99999999";
        when(pasajeroRepositoryPort.findByDocumento(numeroDocumento)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
             buscarPasajeroUseCase.ejecutar(numeroDocumento);
         });
        verify(pasajeroRepositoryPort, times(1)).findByDocumento(numeroDocumento);

        assertTrue(true, "Prueba de excepción para pasajero no encontrado configurada correctamente.");
    }
}