package com.donaton.donaciones.service;

import com.donaton.donaciones.factory.DonacionFactory;
import com.donaton.donaciones.factory.DonacionTipo;
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

        DonacionTipo tipo = DonacionFactory.crear(donacion.getTipo());

        System.out.println(tipo.procesar());

        return repository.save(donacion);
    }

    public List<Donacion> listar() {
        return repository.findAll();
    }

}

