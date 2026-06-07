package com.donaton.donaciones.service;

import com.donaton.donaciones.dto.DonacionDTO;
import com.donaton.donaciones.model.CategoriaDonacion;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.model.EstadoDonacion;
import com.donaton.donaciones.repository.DonacionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DonacionServiceTest {

    @Mock
    private DonacionRepository repository;

    @InjectMocks
    private DonacionService service;

    private Donacion donacionPrueba;

    @BeforeEach
    void setUp() {
        donacionPrueba = new Donacion();
        donacionPrueba.setId(1L);
        donacionPrueba.setCantidad(10);
        donacionPrueba.setCategoria(CategoriaDonacion.ALIMENTOS);
        donacionPrueba.setEstado(EstadoDonacion.PENDIENTE);
    }

    @Test
    void guardar_DeberiaConfigurarEstadoYFechaYGuardar() {
        // Given
        when(repository.save(ArgumentMatchers.any(Donacion.class))).thenReturn(donacionPrueba);

        // When
        Donacion resultado = service.guardar(new Donacion());

        // Then
        assertNotNull(resultado);
        assertEquals(EstadoDonacion.PENDIENTE, resultado.getEstado());
        verify(repository, times(1)).save(ArgumentMatchers.any(Donacion.class));
    }

    @Test
    void listar_DeberiaRetornarListaCompleta() {
        // Given
        when(repository.findAll()).thenReturn(List.of(donacionPrueba));

        // When
        List<Donacion> resultado = service.listar();

        // Then
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        verify(repository).findAll();
    }

    @Test
    void obtenerPorUsuario_DeberiaRetornarListaDelUsuario() {
        // Given
        when(repository.findByUsuarioId(100L)).thenReturn(List.of(donacionPrueba));

        // When
        List<Donacion> resultado = service.obtenerPorUsuario(100L);

        // Then
        assertFalse(resultado.isEmpty());
        verify(repository).findByUsuarioId(100L);
    }

    @Test
    void filtrarPorEstado_DeberiaRetornarListaFiltrada() {
        // Given
        when(repository.findByEstado(EstadoDonacion.PENDIENTE)).thenReturn(List.of(donacionPrueba));

        // When
        List<Donacion> resultado = service.filtrarPorEstado(EstadoDonacion.PENDIENTE);

        // Then
        assertFalse(resultado.isEmpty());
        verify(repository).findByEstado(EstadoDonacion.PENDIENTE);
    }

    @Test
    void filtrarPorCategoria_DeberiaRetornarListaFiltrada() {
        // Given
        when(repository.findByCategoria(CategoriaDonacion.ALIMENTOS)).thenReturn(List.of(donacionPrueba));

        // When
        List<Donacion> resultado = service.filtrarPorCategoria(CategoriaDonacion.ALIMENTOS);

        // Then
        assertFalse(resultado.isEmpty());
        verify(repository).findByCategoria(CategoriaDonacion.ALIMENTOS);
    }

    @Test
    void obtenerPorId_CuandoExiste_DeberiaRetornarDonacion() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(donacionPrueba));

        // When
        Donacion resultado = service.obtenerPorId(1L);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
    }

    @Test
    void obtenerPorId_CuandoNoExiste_DeberiaLanzarExcepcion() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.empty());

        // When & Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.obtenerPorId(1L);
        });

        assertEquals("Donación no encontrada", exception.getMessage());
    }

    @Test
    void actualizarEstado_CuandoExiste_DeberiaGuardarYRetornar() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(donacionPrueba));
        when(repository.save(ArgumentMatchers.any(Donacion.class))).thenReturn(donacionPrueba);

        // When: Usamos ACEPTADA, que ya confirmamos que existe en tu Enum
        Donacion resultado = service.actualizarEstado(1L, EstadoDonacion.ACEPTADA);

        // Then
        assertEquals(EstadoDonacion.ACEPTADA, resultado.getEstado());
        verify(repository).save(donacionPrueba);
    }

    @Test
    void cambiarEstado_CuandoExiste_DeberiaActualizarYGuardar() {
        // Given
        when(repository.findById(1L)).thenReturn(Optional.of(donacionPrueba));
        when(repository.save(ArgumentMatchers.any(Donacion.class))).thenReturn(donacionPrueba);

        // When: Usamos EN_LOGISTICA, que también es válido
        Donacion resultado = service.cambiarEstado(1L, EstadoDonacion.EN_LOGISTICA);

        // Then
        assertNotNull(resultado);
        assertEquals(EstadoDonacion.EN_LOGISTICA, resultado.getEstado());
        verify(repository).save(donacionPrueba);
    }

    @Test
    void actualizar_DeberiaMapearDtoYGuardar() {
        // Given
        DonacionDTO dto = new DonacionDTO();
        dto.setCategoria(CategoriaDonacion.ROPA);
        dto.setCantidad(50);
        dto.setDescripcion("Abrigos");
        dto.setNombreDonante("Juan");
        dto.setDireccionRetiro("Calle 1");
        dto.setComuna("Santiago");
        dto.setUsuarioId(5L);

        when(repository.findById(1L)).thenReturn(Optional.of(donacionPrueba));
        when(repository.save(ArgumentMatchers.any(Donacion.class))).thenReturn(donacionPrueba);

        // When
        Donacion resultado = service.actualizar(1L, dto);

        // Then
        assertNotNull(resultado);
        assertEquals(CategoriaDonacion.ROPA, donacionPrueba.getCategoria());
        assertEquals(50, donacionPrueba.getCantidad());
        assertEquals("Abrigos", donacionPrueba.getDescripcion());
        verify(repository).save(donacionPrueba);
    }
}