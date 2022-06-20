package com.gqs.trabalhofinal_gqs.model.state;

public class EntregueState extends State{
    public EntregueState(Contexto contexto) {
        super(contexto);
    }
    @Override
    public String toString() {
        return "Pedido entregue";
    }
}
