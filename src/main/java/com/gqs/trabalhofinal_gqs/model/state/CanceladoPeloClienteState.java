package com.gqs.trabalhofinal_gqs.model.state;

import java.util.Scanner;

public class CanceladoPeloClienteState extends State{
    public CanceladoPeloClienteState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new ReembolsadoState(super.getContexto()));
    }
    @Override
    public String toString() {
        return "Pedido cancelado pelo cliente";
    }
}
