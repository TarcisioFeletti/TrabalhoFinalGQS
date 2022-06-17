package com.gqs.trabalhofinal_gqs.model.state;

public abstract class State {
    private Contexto contexto;

    public State(Contexto contexto) {
        this.contexto = contexto;
    }

    public Contexto getContexto() {
        return contexto;
    }

    public void setContexto(Contexto contexto) {
        this.contexto = contexto;
    }

    public abstract void avancar();
    public abstract void cancelar();
}
