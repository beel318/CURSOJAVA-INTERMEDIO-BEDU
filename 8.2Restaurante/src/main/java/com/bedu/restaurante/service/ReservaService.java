package com.bedu.restaurante.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReservaService {

    private static final Logger logger = LoggerFactory.getLogger(ReservaService.class);
    private final DisponibilidadService disponibilidadService;

    public ReservaService(DisponibilidadService disponibilidadService) {
        this.disponibilidadService = disponibilidadService;
    }

    public boolean realizarReserva(String fecha, int personas) {
        if (personas < 1 || personas > 10) {
            logger.warn("Solicitud inválida para {} personas el {}", personas, fecha);
            return false;
        }

        boolean disponible = disponibilidadService.hayDisponibilidad(fecha, personas);
        if (disponible) {
            logger.info("Reserva confirmada para {} personas el {}", personas, fecha);
        } else {
            logger.info("Reserva rechazada para {} personas el {} (sin disponibilidad)", personas, fecha);
        }

        return disponible;
    }
}

