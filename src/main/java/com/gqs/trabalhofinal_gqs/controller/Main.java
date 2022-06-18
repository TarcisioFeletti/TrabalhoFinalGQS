package com.gqs.trabalhofinal_gqs.controller;

import com.gqs.trabalhofinal_gqs.collection.AvaliacoesCollection;
import com.gqs.trabalhofinal_gqs.collection.PedidosCollection;
import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Avaliacao;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.Produto;
import com.gqs.trabalhofinal_gqs.model.builder.CestaBasicaBuilder;
import com.gqs.trabalhofinal_gqs.model.builder.DiretorBuilder;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int opcao = 0;
        //Produtos adicionados para os testes
        ProdutosCollection estoque = ProdutosCollection.getInstancia();
        estoque.addProduto(new Produto("Lapis", 10, 1.50, "Papelaria"));
        estoque.addProduto(new Produto("Caderno", 10, 10.99, "Papelaria"));
        estoque.addProduto(new Produto("Carne", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Leite", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Ovo", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Arroz", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Feijão", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Canjica", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Trigo", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Açucar", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Cafe", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Oleo", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Maça", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Laranja", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Banana", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Arroz", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Feijao", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Manteiga", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Tomate", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Pao", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Batata", 40, 30, "Comida"));
        do {
            System.out.println("------------------Menu-----------------");
            System.out.println("1- Adicionar produto ao estoque");
            System.out.println("2- Listar produtos em estoque");
            System.out.println("3- Criar pedido");
            System.out.println("4- Consultar pedido");
            System.out.println("5- Listar pedidos");
            System.out.println("6- Adicionar item no pedido");
            System.out.println("7- Remover item do pedido");
            System.out.println("8- Confirmar estado do pedido");
            System.out.println("9- Cancelar pedido");
            System.out.println("10- Cesta básica");
            System.out.println("11- Consultar estado do pedido");
            System.out.println("12- Avaliar estabelecimento");
            System.out.println("13- Sair");
            opcao = sc.nextInt();
            switch (opcao) {
                case 1:
                    String nome, tipo;
                    int quantidade;
                    double preco;
                    System.out.println("Digite o nome do produto:");
                    nome = sc.next();
                    System.out.println("Digite a quantidade:");
                    quantidade = sc.nextInt();
                    System.out.println("Digite o preço do produto:");
                    preco = sc.nextDouble();
                    System.out.println("Digite o tipo do produto:");
                    tipo = sc.next();
                    Produto produto = new Produto(nome, quantidade, preco, tipo);
                    ProdutosCollection.getInstancia().addProduto(produto);
                    break;
                case 2:
                    List<Produto> produtos = ProdutosCollection.getInstancia().getAll();
                    if (produtos.isEmpty()) {
                        System.out.println("Não existe nenhum produto em estoque");
                    } else {
                        for (Produto prod : produtos) {
                            System.out.println("Nome: " + prod.getNome());
                            System.out.println("Quantidade em estoque: " + prod.getQuantidadeEmEstoque());
                            System.out.println("Preço unitário: " + prod.getPrecoUnitario());
                            System.out.println("Tipo: " + prod.getTipo());
                            System.out.println("-----------------------------------------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("Digite o nome do cliente que esta pedindo");
                    nome = sc.next();
                    Cliente cliente = new Cliente(nome);
                    PedidosCollection.getInstancia().addPedido(new Contexto(LocalDateTime.now(), cliente));
                    System.out.println("Pedido criado com sucesso");
                    break;
                case 4:
                    System.out.println("Digite  numero do pedido:");
                    try {
                        Contexto pe = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                        System.out.println("Cliente: " + pe.getCliente().getNome());
                        System.out.println("Numero do pedido: " + pe.getNumero());
                        System.out.println("Data do pedido: " + pe.getData().getDayOfMonth() + "/" + pe.getData().getMonthValue() + "/" + pe.getData().getYear());
                        if (pe.getValor() == 0) {
                            System.out.println("Pedido não finalizado");
                        } else {
                            System.out.println("Valor: " + pe.getValor());
                            System.out.println("Valor dos impostos: " + pe.getValorTotalImpostos());
                            System.out.println("Valor dos descontos: " + pe.getValorTotalDescontos());
                            System.out.println("Valor total a pagar: " + pe.getValorTotalAPagar());
                        }
                        System.out.println("----------Produtos-----------");
                        for (ItemPedido item : pe.getProdutos()) {
                            System.out.println("Nome: " + item.getItem().getNome() + ", Quantidade: " + item.getQuantidade());
                        }
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    List<Contexto> pedidos = PedidosCollection.getInstancia().getAll();
                    if (pedidos.isEmpty()) {
                        System.out.println("Não existe nenhum pedido");
                    } else {
                        for (Contexto pedido : pedidos) {
                            System.out.println("Cliente: " + pedido.getCliente().getNome());
                            System.out.println("Numero do pedido: " + pedido.getNumero());
                            System.out.println("Data do pedido: " + pedido.getData().getDayOfMonth() + "/" + pedido.getData().getMonthValue() + "/" + pedido.getData().getYear());
                            if (pedido.getValor() == 0) {
                                System.out.println("Pedido não finalizado");
                            } else {
                                System.out.println("Valor: " + pedido.getValor());
                                System.out.println("Valor dos impostos: " + pedido.getValorTotalImpostos());
                                System.out.println("Valor dos descontos: " + pedido.getValorTotalDescontos());
                                System.out.println("Valor total a pagar: " + pedido.getValorTotalAPagar());
                                System.out.println("----------Produtos-----------");
                            }
                            for (ItemPedido item : pedido.getProdutos()) {
                                System.out.println("Nome: " + item.getItem().getNome() + ", Quantidade: " + item.getQuantidade());
                            }
                            System.out.println("-----------------------------------------------------------------");
                        }
                    }
                    break;
                case 6:
                    try {
                        System.out.println("Digite o numero do pedido:");
                        Contexto pedido = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                        nome = "sair";
                        List<ItemPedido> itens = new ArrayList<>();
                        do {
                            try {
                                System.out.println("Digite o nome do produto (Digite sair para terminar de digitar os produtos):");
                                nome = sc.next();
                                if (nome.equalsIgnoreCase("sair"))
                                    break;
                                System.out.println("Digite a quantidade");
                                quantidade = sc.nextInt();
                                itens.add(new ItemPedido(pedido, ProdutosCollection.getInstancia().getProduto(nome), quantidade));
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                                nome = "continuar";
                            }
                        } while (!nome.equalsIgnoreCase("sair"));
                        pedido.addItem(itens.toArray(new ItemPedido[itens.size()]));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 7:
                    try {
                        System.out.println("Digite o numero do pedido:");
                        Contexto pedido = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                        nome = "sair";
                        List<ItemPedido> itens = new ArrayList<>();
                        do {
                            try {
                                System.out.println("Digite o nome do produto (Digite sair para terminar de digitar os produtos):");
                                nome = sc.next();
                                if (nome.equalsIgnoreCase("sair"))
                                    break;
                                System.out.println("Digite a quantidade");
                                quantidade = sc.nextInt();
                                itens.add(new ItemPedido(pedido, ProdutosCollection.getInstancia().getProduto(nome), quantidade));
                            } catch (RuntimeException e) {
                                System.out.println(e.getMessage());
                                nome = "continuar";
                            }
                        } while (!nome.equalsIgnoreCase("sair"));
                        pedido.removerItens(itens.toArray(new ItemPedido[itens.size()]));
                    } catch (RuntimeException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    try {
                        System.out.println("Digite o numero do pedido:");
                        Contexto pedido = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                        if(pedido.toString().equalsIgnoreCase("Pedido aguardando pagamento")){
                            Contexto.pagar(pedido);
                        } else if (pedido.toString().equalsIgnoreCase("Pedido cancelado pelo cliente") || pedido.toString().equalsIgnoreCase("Pedido cancelado pelo estabelecimento")) {
                            Contexto.reembolsar(pedido);
                        }
                        pedido.avancar();
                        System.out.println(pedido.toString());
                    }catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 9:
                    try {
                        System.out.println("Digite o numero do pedido:");
                        Contexto pedido = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                        pedido.cancelar();
                        System.out.println(pedido.toString());
                    }catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 10:
                    try {
                        System.out.println("Digite o nome do cliente que esta pedindo");
                        nome = sc.next();
                        cliente = new Cliente(nome);
                        Contexto pedido = new Contexto(LocalDateTime.now(), cliente);
                        CestaBasicaBuilder builder = new CestaBasicaBuilder(cliente);
                        DiretorBuilder diretor = new DiretorBuilder(builder);

                        System.out.println("Digite o tipo da cesta básica (Economica ou premium):");
                        String cesta = sc.next();
                        if (!cesta.equalsIgnoreCase("Economica") && !cesta.equalsIgnoreCase("Premium")) {
                            System.out.println("Tipo de cesta inválido");
                        } else {
                            diretor.make(cesta);
                            pedido = builder.getPedido();
                            PedidosCollection.getInstancia().addPedido(pedido);
                        }
                    }catch(RuntimeException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 11:
                    System.out.println("Digite o numero do pedido");
                    Contexto pedido = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                    System.out.println(pedido.toString());
                    break;
                case 12:
                    System.out.println("Digite o numero do pedido");
                    pedido = PedidosCollection.getInstancia().getPedido(sc.nextInt());
                    AvaliacoesCollection.getInstancia().addAvaliacao(Avaliacao.avaliar(pedido));
                    break;
                default:
                    System.out.println("Opção inválida");
                    break;
            }
        } while (opcao != 12);

    }
}
