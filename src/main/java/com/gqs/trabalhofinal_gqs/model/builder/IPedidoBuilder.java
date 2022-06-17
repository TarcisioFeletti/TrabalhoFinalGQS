package com.gqs.trabalhofinal_gqs.model.builder;

import com.gqs.trabalhofinal_gqs.model.state.Contexto;

public interface IPedidoBuilder {
    void reset();
    void addProdutosDeOrigemAnimal();
    void addGraos();
    void addProdutosIndustrializados();
    void addLegumesEFrutas();
    void addProdutosPremium();
    Contexto getPedido();
}
