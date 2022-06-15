package com.gqs.trabalhofinal_gqs.model.builder;

import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.Pedido;

import java.time.LocalDateTime;

public class CestaBasicaBuilder implements IPedidoBuilder {

    private Pedido pedido;

    public CestaBasicaBuilder(Cliente cliente) {
        this.pedido = new Pedido(LocalDateTime.now(), cliente);
    }

    @Override
    public void reset() {
        this.pedido = new Pedido(LocalDateTime.now(), pedido.getCliente());
    }

    @Override
    public void addProdutosDeOrigemAnimal() {
        this.pedido.addItem(new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Carne"), 5),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Leite"), 3),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Ovo"), 12));
    }

    @Override
    public void addGraos() {
        this.pedido.addItem(new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Arroz"), 10),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Feijão"), 5),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Canjica"), 3),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Trigo"), 3));
    }

    @Override
    public void addProdutosIndustrializados() {
        this.pedido.addItem(new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Açucar"), 5),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Cafe"), 3),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Oleo"), 3));
    }

    @Override
    public void addLegumesEFrutas() {
        this.pedido.addItem(new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Maça"), 6),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Laranja"), 12),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Banana"), 12));
    }

    @Override
    public void addProdutosPremium() {
        this.pedido.addItem(new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Arroz"), 5),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Feijão"), 3),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Manteiga"), 1),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Tomate"), 5),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Pao"), 3),
                new ItemPedido(this.pedido, ProdutosCollection.getInstancia().getProduto("Batata"), 5));
    }

    @Override
    public Pedido getPedido() {
        return this.pedido;
    }


}
