package com.gqs.trabalhofinal_gqs.model.state;

import java.util.Scanner;

public class CanceladoPeloEstabelecimentoState extends State{
    public CanceladoPeloEstabelecimentoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new ReembolsadoState(super.getContexto()));
    }

    @Override
    public void cancelar() {

    }
}
