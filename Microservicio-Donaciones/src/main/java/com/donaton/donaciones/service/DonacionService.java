package com.donaton.donaciones.service;

import com.donaton.donaciones.dto.DonacionDTO;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.model.EstadoDonacion;
import com.donaton.donaciones.model.CategoriaDonacion;
import com.donaton.donaciones.repository.DonacionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonacionService {

    private final DonacionRepository repository;

    public DonacionService(DonacionRepository repository) {
        this.repository = repository;
    }

    public Donacion guardar(Donacion donacion) {

        donacion.setEstado(EstadoDonacion.PENDIENTE);

        donacion.setFechaCreacion(LocalDateTime.now());

        return repository.save(donacion);
    }

    public List<Donacion> listar() {
        return repository.findAll();
    }

    public List<Donacion> obtenerPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<Donacion> filtrarPorEstado(EstadoDonacion estado) {
        return repository.findByEstado(estado);
    }

    public List<Donacion> filtrarPorCategoria(CategoriaDonacion categoria) {
        return repository.findByCategoria(categoria);
    }

    public Donacion actualizarEstado(Long id, EstadoDonacion estado) {

        Donacion donacion = repository.findById(id)
                .orElseThrow();

        donacion.setEstado(estado);

        return repository.save(donacion);
    }

    public Donacion cambiarEstado(Long id, EstadoDonacion estado) {

        Donacion donacion = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Donación no encontrada"));

        donacion.setEstado(estado);

        return repository.save(donacion);
    }

    public Donacion obtenerPorId(Long id) {

        return repository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Donación no encontrada")
                );
    }

    public Donacion actualizar(
            Long id,
    DonacionDTO dto
    ) {

        Donacion donacion =
                repository.findById(id)
                        .orElseThrow();

        donacion.setCategoria(dto.getCategoria());
        donacion.setCantidad(dto.getCantidad());
        donacion.setDescripcion(dto.getDescripcion());
        donacion.setNombreDonante(dto.getNombreDonante());
        donacion.setDireccionRetiro(dto.getDireccionRetiro());
        donacion.setComuna(dto.getComuna());
        donacion.setUsuarioId(dto.getUsuarioId());

        return repository.save(donacion);
    }
}