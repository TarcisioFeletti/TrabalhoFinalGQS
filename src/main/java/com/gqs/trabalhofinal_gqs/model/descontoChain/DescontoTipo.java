package com.gqs.trabalhofinal_gqs.model.descontoChain;

import com.gqs.trabalhofinal_gqs.model.Produto;

public class DescontoTipo implements IDesconto{

    @Override
    public double calcular(Produto produto) {
        if (produto.getTipo().equalsIgnoreCase("papelaria")) {
            return 1;
        } else if (produto.getTipo().equalsIgnoreCase("mochila")){
            return 3;
        }
        return 0;
    }
}
