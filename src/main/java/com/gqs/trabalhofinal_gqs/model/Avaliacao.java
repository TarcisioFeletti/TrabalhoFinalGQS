package com.gqs.trabalhofinal_gqs.model;

public class Avaliacao {
    private Integer nota;
    private String descricao;

    public Avaliacao(Integer nota, String descricao) {
        this.nota = nota;
        this.descricao = descricao;
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }
}
