package com.gqs.trabalhofinal_gqs.model.chain.desconto;

import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;

import java.util.ArrayList;

public class ProcessaDesconto {
    private Contexto pedido;
    private ArrayList<IDesconto> descontos;

    public ProcessaDesconto(Contexto pedido) {
        this.pedido = pedido;
        descontos = new ArrayList<>();
        descontos.add(new DescontoNatal());
        descontos.add(new DescontoTipo());
        descontos.add(new DescontoIncentivo());
    }
    public void calculaDesconto() throws RuntimeException{
        if(pedido.getProdutos().isEmpty()){
            throw new RuntimeException("Não é possível calcular os descontos com um pedido vazio");
        }else{
            double porcentagemDesconto = 0;
            for(ItemPedido item : pedido.getProdutos()){
                for(IDesconto desconto : descontos){
                    porcentagemDesconto += desconto.calcular(item.getItem()) / 100;
                }
                pedido.addDescontos(item.getValorTotal() * porcentagemDesconto);
                porcentagemDesconto = 0;
            }
        }
    }
}
