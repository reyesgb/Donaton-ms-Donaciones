package com.donaton.donaciones.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.options;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CorsConfigTest.TestController.class)
@Import(CorsConfig.class) // Importamos tu configuración real de CORS
@AutoConfigureMockMvc(addFilters = false) // Apagamos Security para enfocarnos solo en CORS
public class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void corsConfig_DeberiaPermitirOrigenViteLocalhost5173() throws Exception {
        // Simulamos una petición "Preflight" (OPTIONS) que hace el navegador desde tu Front
        mockMvc.perform(options("/ruta-de-prueba")
                        .header("Origin", "http://localhost:5173")
                        .header("Access-Control-Request-Method", "GET"))
                .andExpect(status().isOk())
                // Validamos que la respuesta de tu backend incluya el permiso para ese origen
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:5173"));
    }

    // Un controlador falso solo para poder probar que la regla CORS se aplica a los endpoints
    @RestController
    static class TestController {
        @GetMapping("/ruta-de-prueba")
        public String prueba() {
            return "ok";
        }
    }
}