package com.gqs.trabalhofinal_gqs.model.state;

public class CanceladoPeloEstabelecimentoState extends State{
    public CanceladoPeloEstabelecimentoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new ReembolsadoState(super.getContexto()));
    }
    @Override
    public String toString() {
        return "Pedido cancelado pelo estabelecimento";
    }
}
