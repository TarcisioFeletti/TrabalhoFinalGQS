package com.gqs.trabalhofinal_gqs.model.chain.desconto;

import com.gqs.trabalhofinal_gqs.model.Produto;

public class DescontoIncentivo implements IDesconto {

    @Override
    public double calcular(Produto produto) {
        if(produto.getNome().equalsIgnoreCase("lapis")){
            return 0.5;
        }else if(produto.getNome().equalsIgnoreCase("borracha")){
            return 1;
        }else if(produto.getNome().equalsIgnoreCase("caderno")){
            return 2;
        }
        return 0;
    }
}
