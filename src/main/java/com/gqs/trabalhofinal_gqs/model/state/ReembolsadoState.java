package com.gqs.trabalhofinal_gqs.model.state;

public class ReembolsadoState extends State{
    public ReembolsadoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public String toString() {
        return "Pedido reembolsado";
    }
}
