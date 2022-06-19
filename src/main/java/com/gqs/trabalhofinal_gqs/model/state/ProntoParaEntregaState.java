package com.gqs.trabalhofinal_gqs.model.state;

public class ProntoParaEntregaState extends State{
    public ProntoParaEntregaState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new EmRotaDeEntregaState(super.getContexto()));
    }

    @Override
    public void cancelar() {
        super.getContexto().repor();
        super.getContexto().changeEstado(new CanceladoPeloEstabelecimentoState(super.getContexto()));
    }

    @Override
    public String toString() {
        return "Pedido pronto para entrega";
    }
}
