package com.donaton.donaciones.client;

import org.springframework.stereotype.Component;

@Component
public class NecesidadesClient {

    public String obtenerEstadoNecesidades() {
        // Simulamos falla
        throw new RuntimeException("Servicio de necesidades no disponible");
    }
}