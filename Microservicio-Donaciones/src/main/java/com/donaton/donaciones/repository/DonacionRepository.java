package com.donaton.donaciones.repository;

import com.donaton.donaciones.model.Donacion;
import com.donaton.donaciones.model.EstadoDonacion;
import com.donaton.donaciones.model.CategoriaDonacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonacionRepository extends JpaRepository<Donacion, Long> {

    List<Donacion> findByUsuarioId(Long usuarioId);

    List<Donacion> findByEstado(EstadoDonacion estado);

    List<Donacion> findByCategoria(CategoriaDonacion categoria);

}