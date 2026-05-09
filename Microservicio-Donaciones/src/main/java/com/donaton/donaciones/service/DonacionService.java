package com.donaton.donaciones.service;

import com.donaton.donaciones.client.NecesidadesClient;
import com.donaton.donaciones.factory.DonacionFactory;
import com.donaton.donaciones.factory.DonacionTipo;
import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.repository.DonacionRepository;
import org.springframework.stereotype.Service;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import java.util.List;

@Service
public class DonacionService {

    private final DonacionRepository repository;
    private final NecesidadesClient necesidadesClient;

    // Constructor único con inyección de dependencias
    public DonacionService(DonacionRepository repository, NecesidadesClient necesidadesClient) {
        this.repository = repository;
        this.necesidadesClient = necesidadesClient;
    }

    // Guardar una donación
    public Donacion guardar(Donacion donacion) {

        DonacionTipo tipo = DonacionFactory.crear(donacion.getTipo());

        System.out.println(tipo.procesar());

        return repository.save(donacion);
    }

    // Listar todas las donaciones
    public List<Donacion> listar() {
        return repository.findAll();
    }

    // Verificar necesidades con protección de circuito
    @CircuitBreaker(name = "necesidadesService", fallbackMethod = "fallbackNecesidades")
    public String verificarNecesidades() {
        return necesidadesClient.obtenerEstadoNecesidades();
    }

    // Método si el servicio falla
    public String fallbackNecesidades(Exception e) {
        return "Servicio de necesidades no disponible, intentando más tarde";
    }

    public Donacion obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
