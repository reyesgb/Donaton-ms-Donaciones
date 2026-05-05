package com.donaton.donaciones.dto;

import lombok.Data;

@Data
public class DonacionDTO {
    private String tipo;
    private int cantidad;
    private String origen;
}