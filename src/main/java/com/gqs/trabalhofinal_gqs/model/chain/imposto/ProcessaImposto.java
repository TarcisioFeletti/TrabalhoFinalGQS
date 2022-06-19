package com.gqs.trabalhofinal_gqs.model.chain.imposto;

import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;

import java.util.ArrayList;

public class ProcessaImposto {
    private Contexto pedido;
    private ArrayList<IImposto> impostos;

    public ProcessaImposto(Contexto pedido) {
        this.pedido = pedido;
        impostos = new ArrayList<>();
        impostos.add(new ICMSChain());
        impostos.add(new ISSChain());
        impostos.add(new ImpostoTipoChain());
    }
    public void calculaImpostos() throws RuntimeException{
        if(pedido.getProdutos().isEmpty()){
            throw new RuntimeException("Não é possível calcular os impostos com um pedido vazio");
        }else{
            double porcentagemDesconto = 0;
            for(ItemPedido item : pedido.getProdutos()){
                for(IImposto imposto : impostos){
                    porcentagemDesconto += imposto.calcular(item.getItem()) / 100;
                }
                pedido.addImposto(item.getValorTotal() * porcentagemDesconto);
                porcentagemDesconto = 0;
            }
        }
    }
}
