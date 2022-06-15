package com.gqs.trabalhofinal_gqs.collection;

import com.gqs.trabalhofinal_gqs.model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutosCollection {
    private List<Produto> produtos;
    private static ProdutosCollection instancia;

    private ProdutosCollection() {
        produtos = new ArrayList();
    }

    public static ProdutosCollection getInstancia() {
        if (instancia == null) {
            instancia = new ProdutosCollection();
        }
        return instancia;
    }

    public void addProduto(Produto produto) throws RuntimeException {
        if (produtos.contains(produto)) {
            throw new RuntimeException("O produto " + produto.getNome() + " já se encontra em estoque");
        } else {
            this.produtos.add(produto);
        }
    }

    public Produto getProduto(String nome) throws RuntimeException {
        if (produtos.isEmpty()) {
            throw new RuntimeException("Estoque vazio, adicione um item primeiro");
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    return produto;
                }
            }
            throw new RuntimeException("O produto " + nome + " não se encontra em estoque");
        }
    }

    public void removeProduto(String nome) throws RuntimeException {
        if (produtos.isEmpty()) {
            throw new RuntimeException("Estoque vazio, adicione um item primeiro");
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    produtos.remove(produto);
                }
            }
            throw new RuntimeException("O produto " + nome + " não se encontra em estoque");
        }
    }

    public void vender(String nome, int quantidade) throws RuntimeException {
        boolean vendeu = false;
        if (produtos.isEmpty()) {
            throw new RuntimeException("Estoque vazio, adicione um item primeiro");
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    try {
                        produto.vender(quantidade);
                        vendeu = true;
                        if (produto.getQuantidadeEmEstoque() == 0) {
                            removeProduto(produto.getNome());
                        }
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }
            }
            if (!vendeu) {
                throw new RuntimeException("O produto " + nome + " não se encontra em estoque");
            }
        }
    }

    public void reporEstoque(String nome, int quantidade) throws RuntimeException {
        if (produtos.isEmpty()) {
            throw new RuntimeException("Estoque vazio, adicione um item primeiro");
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    try {
                        produto.reporEstoque(quantidade);
                    } catch (RuntimeException e) {
                        throw e;
                    }
                }
            }
            throw new RuntimeException("Esse produto não existe");
        }
    }
}
