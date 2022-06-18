package com.gqs.trabalhofinal_gqs.model.state;

import com.gqs.trabalhofinal_gqs.collection.AvaliacoesCollection;
import com.gqs.trabalhofinal_gqs.model.Avaliacao;

import java.util.Scanner;

public class EntregueState extends State{
    public EntregueState(Contexto contexto) {
        super(contexto);
    }
    @Override
    public void avancar() {throw new UnsupportedOperationException("Operação não suportada");}
    @Override
    public void cancelar() {
        throw new UnsupportedOperationException("Operação não suportada");
    }
    @Override
    public String toString() {
        return "Pedido entregue";
    }
}
