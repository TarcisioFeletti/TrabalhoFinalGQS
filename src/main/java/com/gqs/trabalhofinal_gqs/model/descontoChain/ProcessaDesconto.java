package com.gqs.trabalhofinal_gqs.model.descontoChain;

import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.Pedido;
import com.gqs.trabalhofinal_gqs.model.Produto;

import java.util.ArrayList;

public class ProcessaDesconto {
    private Pedido pedido;
    private ArrayList<IDesconto> descontos;

    public ProcessaDesconto(Pedido pedido) {
        this.pedido = pedido;
        descontos = new ArrayList<>();
        descontos.add(new DescontoNatal());
        descontos.add(new DescontoTipo());
        descontos.add(new DescontoIncentivo());
    }
    public void calculaDesconto() throws RuntimeException{
        if(pedido.getProdutos().size() == 0){
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
            pedido.calcularValorTotal();
        }
    }
}
