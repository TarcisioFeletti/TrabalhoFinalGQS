package com.gqs.trabalhofinal_gqs.controller;

import com.gqs.trabalhofinal_gqs.collection.AvaliacoesCollection;
import com.gqs.trabalhofinal_gqs.model.Avaliacao;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        Contexto pedido = new Contexto(LocalDateTime.now(), new Cliente("Tarcisio"));
        //Reembolso
        Scanner sc = new Scanner(System.in);
        double valor;
        boolean valorCerto = false;
        do {
            System.out.println("------------------Reembolse o cliente------------------");
            System.out.println("Digite o valor do reembolso para continuar (ou -1 para consultar o valor):");
            valor = sc.nextDouble();
            if(valor == pedido.getValorTotalAPagar()){
                valorCerto = true;
            }else if(valor == -1){
                System.out.println("Valor a ser reembolsado: " + pedido.getValorTotalAPagar());
            }
        }while(valorCerto == false);

        //Pagamento
        //double valor;
        //boolean valorCerto = false;
        do {
            System.out.println("------------------Aguardando pagamento------------------");
            System.out.println("Digite o valor do pagamento para continuar (ou -1 para consultar o valor):");
            valor = sc.nextDouble();
            if(valor == pedido.getValorTotalAPagar()){
                valorCerto = true;
            }else if(valor == -1){
                System.out.println("Valor a ser pago: " + pedido.getValorTotalAPagar());
            }
        }while(valorCerto == false);

        //Avaliação
        int nota;
        String descricao;
        System.out.println("Digite uma nota para o estabelecimento (1 a 10):");
        nota = sc.nextInt();
        System.out.println("Digite a descrição da avaliação");
        descricao = sc.nextLine();

        AvaliacoesCollection.getInstancia().addAvaliacao(new Avaliacao(nota, descricao));
    }
}
