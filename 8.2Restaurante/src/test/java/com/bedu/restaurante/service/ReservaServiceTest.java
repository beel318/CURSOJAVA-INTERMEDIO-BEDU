package com.bedu.restaurante.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class ReservaServiceTest {

    private DisponibilidadService disponibilidadMock;
    private ReservaService reservaService;

    @BeforeEach
    void setup() {
        disponibilidadMock = mock(DisponibilidadService.class);
        reservaService = new ReservaService(disponibilidadMock);
    }

    @Test
    void reservaConfirmadaSiHayDisponibilidad() {
        when(disponibilidadMock.hayDisponibilidad("2025-05-01", 2)).thenReturn(true);

        boolean resultado = reservaService.realizarReserva("2025-05-01", 2);

        assertTrue(resultado);
        verify(disponibilidadMock).hayDisponibilidad("2025-05-01", 2);
    }

    @Test
    void reservaRechazadaSiNoHayDisponibilidad() {
        when(disponibilidadMock.hayDisponibilidad("2025-05-02", 5)).thenReturn(false);

        boolean resultado = reservaService.realizarReserva("2025-05-02", 5);

        assertFalse(resultado);
        verify(disponibilidadMock).hayDisponibilidad("2025-05-02", 5);
    }

    @Test
    void reservaRechazadaPorExcesoDePersonas() {
        boolean resultado = reservaService.realizarReserva("2025-05-03", 15);

        assertFalse(resultado);
        verify(disponibilidadMock, never()).hayDisponibilidad(anyString(), anyInt());
    }

    @Test
    void reservaRechazadaPorCeroPersonas() {
        boolean resultado = reservaService.realizarReserva("2025-05-04", 0);

        assertFalse(resultado);
        verify(disponibilidadMock, never()).hayDisponibilidad(anyString(), anyInt());
    }
}
