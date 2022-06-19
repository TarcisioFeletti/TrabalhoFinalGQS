package com.gqs.trabalhofinal_gqs.model.state;

public abstract class State implements IState{
    private Contexto contexto;

    public State(Contexto contexto) {
        this.contexto = contexto;
    }

    public Contexto getContexto() {
        return contexto;
    }

    @Override
    public void avancar(){
        throw new UnsupportedOperationException("Operação não suportada");
    }
    @Override
    public void cancelar(){
        throw new UnsupportedOperationException("Operação não suportada");
    }
    @Override
    public abstract String toString();
}
