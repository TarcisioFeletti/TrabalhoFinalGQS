package com.gqs.trabalhofinal_gqs.collection;

import com.gqs.trabalhofinal_gqs.model.Avaliacao;

import java.util.ArrayList;
import java.util.List;

public class AvaliacoesCollection {
    private List<Avaliacao> avaliacoes;
    private static AvaliacoesCollection instancia;

    private AvaliacoesCollection(){
        this.avaliacoes = new ArrayList<>();
    }

    public static AvaliacoesCollection getInstancia(){
        if(instancia == null) {
            instancia = new AvaliacoesCollection();
        }
        return instancia;
    }

    public void addAvaliacao(Avaliacao avaliacao){
        this.avaliacoes.add(avaliacao);
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
}
