package com.donaton.donaciones.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = RestTemplateConfig.class)
public class RestTemplateConfigTest {

    @Autowired
    private ApplicationContext context;

    @Test
    void restTemplateBean_DeberiaEstarPresenteEnElContexto() {
        // Ejecución: Le pedimos a Spring que nos dé el Bean que tu configuraste
        RestTemplate restTemplate = context.getBean(RestTemplate.class);

        // Validación: Comprobamos que no sea nulo, es decir, que se creó con éxito
        assertNotNull(restTemplate, "El bean RestTemplate debe ser instanciado por la configuración");
    }
}