package com.gqs.trabalhofinal_gqs.model.chain.imposto;

import com.gqs.trabalhofinal_gqs.model.Produto;

public class ICMSChain implements IImposto {
    @Override
    public double calcular(Produto produto) {
        return 7;
    }
}
