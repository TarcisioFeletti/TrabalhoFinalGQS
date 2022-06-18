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
    public abstract void avancar();
    @Override
    public abstract void cancelar();
    @Override
    public abstract String toString();
}
