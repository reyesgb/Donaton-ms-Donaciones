package com.donaton.donaciones.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NecesidadesClient {

    private final RestTemplate restTemplate;

    public NecesidadesClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String obtenerNecesidades() {

        return restTemplate.getForObject(
                "http://localhost:8081/necesidades",
                String.class
        );
    }
}