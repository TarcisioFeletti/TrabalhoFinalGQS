package com.gqs.trabalhofinal_gqs.model;

public class Produto {
    private String nome;
    private int quantidadeEmEstoque;
    private double precoUnitario;
    private String tipo;

    public Produto(String nome, int quantidadeEmEstoque, double precoUnitario, String tipo) {
        this.nome = nome;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.precoUnitario = precoUnitario;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public String getTipo() {
        return tipo;
    }
}
