package com.donaton.donaciones.service;

import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.repository.DonacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonacionService {

    private final DonacionRepository repository;

    public DonacionService(DonacionRepository repository) {
        this.repository = repository;
    }

    public Donacion guardar(Donacion donacion) {
        return repository.save(donacion);
    }

    public List<Donacion> listar() {
        return repository.findAll();
    }

}

