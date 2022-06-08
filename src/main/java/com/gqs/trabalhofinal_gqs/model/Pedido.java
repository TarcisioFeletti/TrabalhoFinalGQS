package com.gqs.trabalhofinal_gqs.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Pedido {
    private int numero;
    private LocalDateTime data;
    private double valor;
    private double valorTotalImpostos;
    private double valorTotalAPagar;
    private double valorTotalDescontos;
    private ArrayList<ItemPedido> produtos;

    public Pedido(int numero, LocalDateTime data, double valorTotalImpostos, ArrayList<ItemPedido> produtos) {
        this.numero = numero;
        this.data = data;
        this.produtos = produtos;
        this.valor = 0;
        for(ItemPedido item : produtos){
            this.valor += item.getValorTotal();
        }
        this.valorTotalImpostos = valorTotalImpostos;
        this.valorTotalAPagar = valor + valorTotalImpostos;
        this.valorTotalDescontos = 0;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getData() {
        return data;
    }

    public double getValor() {
        return valor;
    }

    public double getValorTotalImpostos() {
        return valorTotalImpostos;
    }

    public double getValorTotalAPagar() {
        return valorTotalAPagar;
    }

    public void addDescontos(double valorDesconto) {
        this.valorTotalDescontos += valorDesconto;
    }

    public ArrayList<ItemPedido> getProdutos() {
        return produtos;
    }

    public void addItem(ItemPedido item){
        this.produtos.add(item);
        this.valor += item.getValorTotal();
        this.valorTotalAPagar += item.getValorTotal();
    }

    public void calcularValorTotal(){
        this.valorTotalAPagar = this.valor + this.valorTotalImpostos - this.valorTotalDescontos;
    }
}
