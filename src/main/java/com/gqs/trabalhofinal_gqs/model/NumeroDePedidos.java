package com.gqs.trabalhofinal_gqs.model;

public class NumeroDePedidos {
    private static int numero = 0;

    private NumeroDePedidos(){}

    public static int getNumero(){
        numero++;
        return numero;
    }
}
