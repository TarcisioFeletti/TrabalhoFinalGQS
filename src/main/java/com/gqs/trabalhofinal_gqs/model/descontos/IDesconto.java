package com.gqs.trabalhofinal_gqs.model.descontos;

import com.gqs.trabalhofinal_gqs.model.Produto;

public interface IDesconto {
    public double calcular(Produto produto);
}
