package com.gqs.trabalhofinal_gqs.model.state;

import com.gqs.trabalhofinal_gqs.collection.AvaliacoesCollection;
import com.gqs.trabalhofinal_gqs.model.Avaliacao;

import java.util.Scanner;

public class EntregueState extends State{
    public EntregueState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {}

    @Override
    public void cancelar() {}
}
