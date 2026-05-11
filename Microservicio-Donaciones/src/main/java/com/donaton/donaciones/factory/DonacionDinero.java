package com.donaton.donaciones.factory;

public class DonacionDinero implements DonacionTipo {

    @Override
    public String procesar() {
        return "Procesando donacion de dinero";
    }
}