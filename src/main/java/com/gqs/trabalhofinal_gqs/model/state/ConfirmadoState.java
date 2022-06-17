package com.gqs.trabalhofinal_gqs.model.state;

public class ConfirmadoState extends State{
    public ConfirmadoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new ProntoParaEntregaState(super.getContexto()));
    }

    @Override
    public void cancelar() {
        super.getContexto().cancelar();
        super.getContexto().changeEstado(new CanceladoPeloEstabelecimentoState(super.getContexto()));
    }
}
