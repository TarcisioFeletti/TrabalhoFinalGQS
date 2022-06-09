package com.gqs.trabalhofinal_gqs.model;

public class Imposto {
    private String nome;
    private double percentual;

    public Imposto(String nome, double percentual) {
        this.nome = nome;
        this.percentual = percentual;
    }

    public String getNome() {
        return nome;
    }

    public double getPercentual() {
        return percentual;
    }
}
