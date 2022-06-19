package com.gqs.trabalhofinal_gqs.model.chain.imposto;

import com.gqs.trabalhofinal_gqs.model.Produto;

public class ISSChain implements IImposto {
    @Override
    public double calcular(Produto produto) {
        return 2;
    }
}
