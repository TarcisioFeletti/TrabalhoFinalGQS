package com.gqs.trabalhofinal_gqs.model.state;

public class EmRotaDeEntregaState extends State{
    public EmRotaDeEntregaState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().changeEstado(new EntregueState(super.getContexto()));
    }
    @Override
    public String toString() {
        return "Pedido em rota de entrega";
    }
}
