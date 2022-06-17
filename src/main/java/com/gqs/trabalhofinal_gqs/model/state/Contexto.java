package com.gqs.trabalhofinal_gqs.model.state;

import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.Imposto;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.NumeroDePedidos;
import com.gqs.trabalhofinal_gqs.model.descontos.ProcessaDesconto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Contexto {
    private int numero;
    private LocalDateTime data;
    private double valor;
    private double valorTotalImpostos;
    private double valorTotalAPagar;
    private double valorTotalDescontos;
    private List<ItemPedido> produtos;
    private List<Imposto> impostos;
    private Cliente cliente;
    private State estado;

    public Contexto(LocalDateTime data, Cliente cliente) {
        this.numero = NumeroDePedidos.getNumero();
        this.data = data;
        this.produtos = new ArrayList<>();
        this.impostos = new ArrayList<>();
        this.valor = 0;
        this.valorTotalImpostos = 0;
        this.valorTotalDescontos = 0;
        this.cliente = cliente;
        estado = new NovoState(this);
    }

    //Gets
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
    public List<ItemPedido> getProdutos() {
        return produtos;
    }
    public double getValorTotalDescontos() {return valorTotalDescontos;}
    public List<Imposto> getImpostos() {return impostos;}
    public Cliente getCliente() {return cliente;}
    public State getEstado() {
        return estado;
    }
    public void changeEstado(State estado) {
        this.estado = estado;
    }

    //Adições
    public void addItem(ItemPedido... itens) {
        for(ItemPedido item : itens){
            try {
                this.produtos.add(item);
                ProdutosCollection.getInstancia().vender(item.getItem().getNome(), item.getQuantidade());
            }catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }
    public void addImposto(Imposto... impostos){
        for(Imposto imposto : impostos){
            this.impostos.add(imposto);
        }
    }
    public void addDescontos(double valorDesconto) {
        this.valorTotalDescontos += valorDesconto;
    }

    public void calcularDescontos(){
        ProcessaDesconto processadora = new ProcessaDesconto(this);
        processadora.calculaDesconto();
    }

    //Cálculos
    public void recalcularValores() {
        this.valor = 0;
        for (ItemPedido item : produtos) {
            this.valor += item.getValorTotal();
        }
        this.valorTotalImpostos = 0;
        for (Imposto imposto : impostos) {
            this.valorTotalImpostos += (imposto.getPercentual()/100) * this.valor;
        }
        this.valorTotalAPagar = valor + valorTotalImpostos - this.valorTotalDescontos;
    }

    public void removerItens(ItemPedido... itens){
        for(ItemPedido item : itens){
            try {
                this.produtos.remove(item);
                ProdutosCollection.getInstancia().reporEstoque(item.getItem().getNome(), item.getQuantidade());
            }catch(RuntimeException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public void cancelar(){
        for(ItemPedido item : produtos){
            ProdutosCollection.getInstancia().reporEstoque(item.getItem().getNome(), item.getQuantidade());
        }
    }
}
