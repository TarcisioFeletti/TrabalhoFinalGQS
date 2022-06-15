package com.gqs.trabalhofinal_gqs.model;

public class ItemPedido {
    private Pedido pedido;
    private Produto item;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;

    public ItemPedido(Pedido pedido, Produto item, int quantidade) {
        this.pedido = pedido;
        this.item = item;
        this.quantidade = quantidade;
        this.valorUnitario = item.getPrecoUnitario();
        this.valorTotal = this.quantidade * this.valorUnitario;
    }

    public Pedido getPedido() {
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
}
