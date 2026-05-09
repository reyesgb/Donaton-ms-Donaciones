package com.donaton.donaciones.factory;

public class DonacionFactory {

    public static DonacionTipo crear(String tipo) {

        if (tipo.equalsIgnoreCase("ALIMENTO")) {
            return new DonacionAlimento();
        }

        if (tipo.equalsIgnoreCase("DINERO")) {
            return new DonacionDinero();
        }

        throw new RuntimeException("Tipo de donacion no valido");
    }
}