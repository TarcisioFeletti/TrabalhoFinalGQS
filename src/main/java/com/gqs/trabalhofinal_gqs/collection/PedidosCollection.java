package com.gqs.trabalhofinal_gqs.collection;

import com.gqs.trabalhofinal_gqs.model.state.Contexto;

import java.util.ArrayList;
import java.util.List;

public class PedidosCollection {
    private List<Contexto> pedidos;
    private static PedidosCollection instancia = null;

    private PedidosCollection(List<Contexto> pedidos) {
        this.pedidos = pedidos;
    }

    public static PedidosCollection getInstancia() {
        if (instancia == null) {
            instancia = new PedidosCollection(new ArrayList<Contexto>());
        }
        return instancia;
    }

    public void addPedido(Contexto pedido){
        this.pedidos.add(pedido);
    }

    public List<Contexto> getAll(){
        return pedidos;
    }

    public Contexto getPedido(int valor) throws RuntimeException{
        if (pedidos.isEmpty()) {
            throw new RuntimeException("Não existe nenhum pedido");
        } else {
            for (Contexto pedido : pedidos) {
                if (valor == pedido.getNumero()) {
                    return pedido;
                }
            }
            throw new RuntimeException("Esse pedido não está listado");
        }
    }
}
