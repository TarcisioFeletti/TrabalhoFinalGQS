package com.gqs.trabalhofinal_gqs.model.builder;

public class DiretorBuilder {
    private IPedidoBuilder builder;

    public DiretorBuilder(IPedidoBuilder builder){
        this.builder = builder;
    }

    public void setBuilder(IPedidoBuilder builder) {
        this.builder = builder;
    }

    public void make(String tipo){
        builder.reset();
        builder.addProdutosDeOrigemAnimal();
        builder.addGraos();
        builder.addProdutosIndustrializados();
        builder.addLegumesEFrutas();
        if(tipo.equalsIgnoreCase("Premium")){
            builder.addProdutosPremium();
        }
    }
}
