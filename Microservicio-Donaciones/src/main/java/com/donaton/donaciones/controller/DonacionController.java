package com.donaton.donaciones.controller;

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
    public Donacion crear(@RequestBody Donacion donacion) {
        return service.guardar(donacion);
    }

    @GetMapping
    public List<Donacion> listar() {
        return service.listar();
    }

    @GetMapping("/estado-necesidades")
    public String estado() {
        return service.verificarNecesidades();
    }
}
