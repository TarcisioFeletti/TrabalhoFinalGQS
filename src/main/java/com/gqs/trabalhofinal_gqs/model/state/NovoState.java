package com.gqs.trabalhofinal_gqs.model.state;

public class NovoState extends State{

    public NovoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().recalcularValores();
        super.getContexto().changeEstado(new AguardandoPagamentoState(super.getContexto()));
    }

    @Override
    public void cancelar() {
        super.getContexto().repor();
        super.getContexto().changeEstado(new CanceladoPeloClienteState(super.getContexto()));
    }

    @Override
    public String toString() {
        return "Novo pedido";
    }
}
