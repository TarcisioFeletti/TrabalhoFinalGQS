package com.gqs.trabalhofinal_gqs.model;

import com.gqs.trabalhofinal_gqs.collection.AvaliacoesCollection;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;

import java.util.Scanner;

public class Avaliacao {
    private Contexto pedido;
    private Integer nota;
    private String descricao;

    public Avaliacao(Integer nota, String descricao, Contexto pedido) {
        this.nota = nota;
        this.descricao = descricao;
        this.pedido = pedido;
    }

    public Avaliacao(Avaliacao avaliacao) {
        this.nota = avaliacao.getNota();
        this.descricao = avaliacao.getDescricao();
    }

    public Integer getNota() {
        return nota;
    }

    public String getDescricao() {
        return descricao;
    }

    public static Avaliacao avaliar(Contexto pedido){
        Scanner sc = new Scanner(System.in);
        int nota;
        String descricao;
        System.out.println("-----------------Avaliação do estabelecimento-----------------");
        System.out.println("Digite uma nota para o estabelecimento (1-10): ");
        nota = sc.nextInt();
        System.out.println("Digite a descrição da avaliação");
        descricao = sc.next();
        Avaliacao avaliacao = new Avaliacao(nota, descricao, pedido);
        return avaliacao;
    }
}
