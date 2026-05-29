package com.donaton.donaciones.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "donaciones")
public class Donacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CategoriaDonacion categoria;

    private Integer cantidad;

    private String descripcion;

    private String nombreDonante;

    private String direccionRetiro;

    private String comuna;

    @Enumerated(EnumType.STRING)
    private EstadoDonacion estado;

    private String comentarioRevision;

    private LocalDateTime fechaCreacion;

    private Long usuarioId;
}