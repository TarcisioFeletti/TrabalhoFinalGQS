package com.gqs.trabalhofinal_gqs.model.state;

import java.util.Scanner;

public class AguardandoPagamentoState extends State{
    public AguardandoPagamentoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new ConfirmadoState(super.getContexto()));
    }

    @Override
    public void cancelar() {
        super.getContexto().cancelar();
        super.getContexto().changeEstado(new CanceladoPeloClienteState(super.getContexto()));
    }
}
