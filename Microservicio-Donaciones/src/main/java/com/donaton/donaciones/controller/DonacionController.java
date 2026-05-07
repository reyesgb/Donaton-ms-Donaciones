package com.donaton.donaciones.controller;

import com.donaton.donaciones.dto.DonacionDTO;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.service.DonacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/donaciones")
public class DonacionController {

    private final DonacionService service;

    public DonacionController(DonacionService service) {
        this.service = service;
    }

    @PostMapping
    public Donacion crear(@RequestBody DonacionDTO dto) {

        Donacion d = new Donacion();
        d.setTipo(dto.getTipo());
        d.setCantidad(dto.getCantidad());
        d.setOrigen(dto.getOrigen());

        return service.guardar(d);
    }

    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    @GetMapping("/estado-necesidades")
    public String estado() {
        return service.verificarNecesidades();
    }

    @GetMapping("/{id}")
    public Donacion obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
