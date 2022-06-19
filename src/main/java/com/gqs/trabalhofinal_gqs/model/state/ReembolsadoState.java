package com.gqs.trabalhofinal_gqs.model.state;

public class ReembolsadoState extends State{
    public ReembolsadoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public void cancelar() {
        throw new UnsupportedOperationException("Operação não suportada");
    }

    @Override
    public String toString() {
        return "Pedido reembolsado";
    }
}
