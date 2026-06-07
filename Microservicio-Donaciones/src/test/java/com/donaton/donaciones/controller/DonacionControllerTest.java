package com.donaton.donaciones.controller;

import com.donaton.donaciones.dto.DonacionDTO;
import com.donaton.donaciones.model.CategoriaDonacion;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.model.EstadoDonacion;
import com.donaton.donaciones.service.DonacionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DonacionController.class)
@AutoConfigureMockMvc(addFilters = false) // Desactiva filtros de Security para testear el endpoint puro
public class DonacionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DonacionService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void listar_DeberiaRetornarListaYStatus200() throws Exception {
        // Given
        Donacion d1 = new Donacion();
        d1.setId(1L);
        Donacion d2 = new Donacion();
        d2.setId(2L);

        when(service.listar()).thenReturn(Arrays.asList(d1, d2));

        // When & Then
        mockMvc.perform(get("/donaciones")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void obtenerPorId_DeberiaRetornarDonacionYStatus200() throws Exception {
        // Given
        Donacion d = new Donacion();
        d.setId(5L);
        when(service.obtenerPorId(5L)).thenReturn(d);

        // When & Then
        mockMvc.perform(get("/donaciones/5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5));
    }

    @Test
    void crear_DeberiaRetornarDonacionCreadaYStatus200() throws Exception {
        // Given
        DonacionDTO dto = new DonacionDTO();
        dto.setCategoria(CategoriaDonacion.ALIMENTOS); // Corregido: Usando el Enum correcto
        dto.setCantidad(15);
        dto.setDescripcion("Fideos");

        Donacion donacionCreada = new Donacion();
        donacionCreada.setId(10L);
        donacionCreada.setCantidad(15);
        donacionCreada.setCategoria(CategoriaDonacion.ALIMENTOS);

        when(service.guardar(ArgumentMatchers.any(Donacion.class))).thenReturn(donacionCreada);

        // When & Then
        mockMvc.perform(post("/donaciones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.cantidad").value(15));
    }

    @Test
    void cambiarEstado_DeberiaRetornarDonacionActualizadaYStatus200() throws Exception {
        // Given
        Donacion donacionModificada = new Donacion();
        donacionModificada.setId(1L);
        donacionModificada.setEstado(EstadoDonacion.EN_LOGISTICA); // Asumiendo que existe EN_LOGISTICA en tu Enum, si no, cambialo al que uses

        when(service.cambiarEstado(1L, EstadoDonacion.EN_LOGISTICA)).thenReturn(donacionModificada);

        // When & Then
        mockMvc.perform(put("/donaciones/1/estado")
                        .param("estado", "EN_LOGISTICA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("EN_LOGISTICA"));
    }

    @Test
    void actualizar_DeberiaRetornarDonacionYStatus200() throws Exception {
        // Given
        DonacionDTO dto = new DonacionDTO();
        dto.setCantidad(20);

        Donacion donacionActualizada = new Donacion();
        donacionActualizada.setId(1L);
        donacionActualizada.setCantidad(20);

        when(service.actualizar(ArgumentMatchers.eq(1L), ArgumentMatchers.any())).thenReturn(donacionActualizada);

        // When & Then
        mockMvc.perform(put("/donaciones/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cantidad").value(20));
    }
}