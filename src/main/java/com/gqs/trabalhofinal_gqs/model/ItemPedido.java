package com.gqs.trabalhofinal_gqs.model;

import com.gqs.trabalhofinal_gqs.model.state.Contexto;

public class ItemPedido {
    private Contexto pedido;
    private Produto item;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    public ItemPedido(Contexto pedido, Produto item, int quantidade) {
        this.pedido = pedido;
        this.item = item;
        this.quantidade = quantidade;
        this.valorUnitario = item.getPrecoUnitario();
        this.valorTotal = this.quantidade * this.valorUnitario;
    }

    public Contexto getPedido() {
        return pedido;
    }

    public Produto getItem() {
        return item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
