package com.donaton.donaciones.dto;

import com.donaton.donaciones.model.CategoriaDonacion;
import lombok.Data;

@Data
public class DonacionDTO {

    private CategoriaDonacion categoria;

    private Integer cantidad;

    private String descripcion;

    private String nombreDonante;

    private String direccionRetiro;

    private String comuna;

    private Long usuarioId;
}