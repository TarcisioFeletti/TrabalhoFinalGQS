package com.gqs.trabalhofinal_gqs.model.state;

import com.gqs.trabalhofinal_gqs.model.Imposto;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;

public class NovoState extends State{

    public NovoState(Contexto contexto) {
        super(contexto);
    }

    @Override
    public void avancar() {
        super.getContexto().calcularDescontos();
        super.getContexto().recalcularValores();
        super.getContexto().changeEstado(new AguardandoPagamentoState(super.getContexto()));
    }

    @Override
    public void cancelar() {
        super.getContexto().cancelar();
        super.getContexto().changeEstado(new CanceladoPeloClienteState(super.getContexto()));
    }
}
