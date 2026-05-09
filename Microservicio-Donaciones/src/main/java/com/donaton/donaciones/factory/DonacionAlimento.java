package com.donaton.donaciones.factory;

public class DonacionAlimento implements DonacionTipo {

    @Override
    public String procesar() {
        return "Procesando donacion de alimentos";
    }
}