package com.gqs.trabalhofinal_gqs.model.chain.imposto;

import com.gqs.trabalhofinal_gqs.model.Produto;

public class ImpostoTipoChain implements IImposto {
    @Override
    public double calcular(Produto produto) {
        if (produto.getTipo().equalsIgnoreCase("papelaria")) {
            return 5;
        } else if (produto.getTipo().equalsIgnoreCase("comida")){
            return 4;
        }
        return 8;
    }
}
