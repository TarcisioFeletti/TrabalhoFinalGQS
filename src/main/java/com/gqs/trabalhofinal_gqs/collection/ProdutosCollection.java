package com.gqs.trabalhofinal_gqs.collection;

import com.gqs.trabalhofinal_gqs.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutosCollection {
    private List<Produto> produtos;
    private static ProdutosCollection instancia;

    private ProdutosCollection(ArrayList produtos) {
        this.produtos = produtos;
    }

    public static ProdutosCollection getInstancia() {
        if (instancia == null) {
            instancia = new ProdutosCollection(new ArrayList());
        }
        return instancia;
    }

    private RuntimeException lancaExcecaoProduto(String nome) {
        return new RuntimeException("O produto " + nome + " não se encontra em estoque");
    }

    private RuntimeException lancaExcecaoVazio() {
        return new RuntimeException("Estoque vazio, adicione um item primeiro");
    }

    public void addProduto(Produto produto) throws RuntimeException {

        for (Produto produtof : produtos) {
            if (produto.getNome().equalsIgnoreCase(produtof.getNome())) {
                produtof.setQuantidadeEmEstoque(produtof.getQuantidadeEmEstoque() + produto.getQuantidadeEmEstoque());
                return;
            }
        }
        this.produtos.add(produto);
    }

    public Produto getProduto(String nome) throws RuntimeException {
        if (produtos.isEmpty()) {
            throw lancaExcecaoVazio();
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    return produto;
                }
            }
            throw lancaExcecaoProduto(nome);
        }
    }

    public void removeProduto(String nome) throws RuntimeException {
        if (produtos.isEmpty()) {
            throw lancaExcecaoVazio();
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    produtos.remove(produto);
                }
            }
            throw lancaExcecaoProduto(nome);
        }
    }

    public void vender(String nome, int quantidade) throws RuntimeException {
        boolean vendeu = false;
        if (produtos.isEmpty()) {
            throw lancaExcecaoVazio();
        } else {
            try {
                Produto produto = getProduto(nome);
                if (produto != null) {
                    produto.vender(quantidade);
                    vendeu = true;
                    if (produto.getQuantidadeEmEstoque() == 0) {
                        removeProduto(produto.getNome());
                    }
                }
            } catch (RuntimeException e) {
                throw e;
            }
        }
        if (!vendeu) {
            throw lancaExcecaoProduto(nome);
        }
    }


    public void reporEstoque(String nome, int quantidade) throws RuntimeException {
        boolean existe = false;
        if (produtos.isEmpty()) {
            throw lancaExcecaoVazio();
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    try {
                        produto.reporEstoque(quantidade);
                        existe = true;
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }
            }
            if (!existe) {
                throw lancaExcecaoProduto(nome);
            }
        }
    }

    public List<Produto> getAll() {
        return produtos;
    }
}
