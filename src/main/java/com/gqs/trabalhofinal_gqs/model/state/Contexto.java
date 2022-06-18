package com.gqs.trabalhofinal_gqs.model.state;

import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.NumeroDePedidos;
import com.gqs.trabalhofinal_gqs.model.chain.desconto.ProcessaDesconto;
import com.gqs.trabalhofinal_gqs.model.chain.imposto.ProcessaImposto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Contexto implements IState{
    private int numero;
    private LocalDateTime data;
    private double valor;
    private double valorTotalImpostos;
    private double valorTotalAPagar;
    private double valorTotalDescontos;
    private List<ItemPedido> produtos;
    private Cliente cliente;
    private State estado;

    public Contexto(LocalDateTime data, Cliente cliente) {
        this.numero = NumeroDePedidos.getNumero();
        this.data = data;
        this.produtos = new ArrayList<>();
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
    public Cliente getCliente() {return cliente;}
    public State getEstado() {
        return estado;
    }
    public void changeEstado(State estado) {
        this.estado = estado;
    }

    //Adições
    public void addItem(ItemPedido... itens) throws RuntimeException{
        for(ItemPedido item : itens){
            try {
                this.produtos.add(item);
                ProdutosCollection.getInstancia().vender(item.getItem().getNome(), item.getQuantidade());
            }catch(RuntimeException e){
                throw e;
            }
        }
    }
    public void addImposto(double valorImposto){
        this.valorTotalImpostos += valorImposto;
    }
    public void addDescontos(double valorDesconto) {
        this.valorTotalDescontos += valorDesconto;
    }

    //Cálculos
    public void recalcularValores() {
        this.valor = 0;
        for (ItemPedido item : produtos) {
            this.valor += item.getValorTotal();
        }

        ProcessaDesconto processaDesconto = new ProcessaDesconto(this);
        processaDesconto.calculaDesconto();

        ProcessaImposto processaImposto = new ProcessaImposto(this);
        processaImposto.calculaImpostos();

        this.valorTotalAPagar = this.valor + this.valorTotalImpostos - this.valorTotalDescontos;
    }

    public void removerItens(ItemPedido... itens) throws RuntimeException{
        for(ItemPedido item : itens){
            try {
                for(ItemPedido itemLista : produtos){
                    if(itemLista.getItem().getNome().equalsIgnoreCase(item.getItem().getNome())){
                        if(itemLista.getQuantidade() == item.getQuantidade()){
                            this.produtos.remove(itemLista);
                            ProdutosCollection.getInstancia().reporEstoque(item.getItem().getNome(), item.getQuantidade());
                            break;
                        }else if(itemLista.getQuantidade() > item.getQuantidade()){
                            itemLista.setQuantidade(itemLista.getQuantidade() - item.getQuantidade());
                            ProdutosCollection.getInstancia().reporEstoque(item.getItem().getNome(), item.getQuantidade());
                        }else{
                            throw new RuntimeException("Quantidade não suportada");
                        }
                    }
                }
            }catch(RuntimeException e){
                throw e;
            }
        }
    }

    @Override
    public void avancar() {
        estado.avancar();
    }

    @Override
    public void cancelar(){
        estado.cancelar();
    }

    @Override
    public String toString() {
        return estado.toString();
    }

    public void repor(){
        for(ItemPedido item : produtos){
            ProdutosCollection.getInstancia().reporEstoque(item.getItem().getNome(), item.getQuantidade());
        }
    }

    public static void reembolsar(Contexto pedido){
        //Reembolso
        Scanner sc = new Scanner(System.in);
        double valor;
        boolean valorCerto = false;
        do {
            System.out.println("------------------Reembolse o cliente------------------");
            System.out.println("Digite o valor do reembolso para continuar (ou -1 para consultar o valor):");
            valor = sc.nextDouble();
            if (valor == pedido.getValorTotalAPagar()) {
                valorCerto = true;
            } else if (valor == -1) {
                System.out.println("Valor a ser reembolsado: " + pedido.getValorTotalAPagar());
            }
        } while (valorCerto == false);
    }

    public static void pagar(Contexto pedido){
        //Pagamento
        Scanner sc = new Scanner(System.in);
        double valor;
        boolean valorCerto = false;
        do {
            System.out.println("------------------Aguardando pagamento------------------");
            System.out.println("Digite o valor do pagamento para continuar (ou -1 para consultar o valor):");
            valor = sc.nextDouble();
            if (valor == pedido.getValorTotalAPagar()) {
                valorCerto = true;
            } else if (valor == -1) {
                System.out.println("Valor a ser pago: " + pedido.getValorTotalAPagar());
            }
        } while (valorCerto == false);
    }
}
