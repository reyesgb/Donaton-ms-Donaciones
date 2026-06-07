package com.donaton.donaciones.client;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class NecesidadesClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private NecesidadesClient necesidadesClient;

    @Test
    void obtenerNecesidades_DeberiaLlamarAlMicroservicioYRetornarString() {
        // Given
        String urlEsperada = "http://localhost:8081/necesidades";
        String respuestaSimulada = "[{\"id\":1, \"descripcion\":\"Agua y alimentos no perecibles\"}]";

        // Simulamos que cuando el RestTemplate intente ir a esa URL, devolverá nuestro JSON simulado
        when(restTemplate.getForObject(urlEsperada, String.class)).thenReturn(respuestaSimulada);

        // When
        String resultado = necesidadesClient.obtenerNecesidades();

        // Then
        assertEquals(respuestaSimulada, resultado, "El JSON devuelto debe coincidir con el simulado");

        // Verificamos que efectivamente se haya intentado llamar a la URL correcta
        verify(restTemplate).getForObject(urlEsperada, String.class);
    }
}