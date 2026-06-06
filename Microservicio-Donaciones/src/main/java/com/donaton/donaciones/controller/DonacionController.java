package com.donaton.donaciones.controller;

import com.donaton.donaciones.dto.DonacionDTO;
import com.donaton.donaciones.model.*;
import com.donaton.donaciones.service.DonacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donaciones")
@CrossOrigin(origins = "*")
public class DonacionController {

    private final DonacionService service;

    public DonacionController(DonacionService service) {
        this.service = service;
    }

    @PostMapping
    public Donacion crear(@RequestBody DonacionDTO dto) {

        Donacion donacion = new Donacion();

        donacion.setCategoria(dto.getCategoria());
        donacion.setCantidad(dto.getCantidad());
        donacion.setDescripcion(dto.getDescripcion());
        donacion.setNombreDonante(dto.getNombreDonante());
        donacion.setDireccionRetiro(dto.getDireccionRetiro());
        donacion.setComuna(dto.getComuna());
        donacion.setUsuarioId(dto.getUsuarioId());

        return service.guardar(donacion);
    }

    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Donacion obtenerPorId(
            @PathVariable Long id
    ) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}/estado")
    public Donacion cambiarEstado(
            @PathVariable Long id,
            @RequestParam EstadoDonacion estado
    ) {
        return service.cambiarEstado(id, estado);
    }

    @PutMapping("/{id}")
    public Donacion actualizar(
            @PathVariable Long id,
            @RequestBody DonacionDTO dto
    ) {
        return service.actualizar(id, dto);
    }
}