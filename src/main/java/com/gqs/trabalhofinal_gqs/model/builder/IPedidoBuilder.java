package com.gqs.trabalhofinal_gqs.model.builder;

import com.gqs.trabalhofinal_gqs.model.Pedido;

public interface IPedidoBuilder {
    void reset();
    void addProdutosDeOrigemAnimal();
    void addGraos();
    void addProdutosIndustrializados();
    void addLegumesEFrutas();
    void addProdutosPremium();
    Pedido getPedido();
}
