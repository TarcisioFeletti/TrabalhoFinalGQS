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

    public RuntimeException lancaExcecaoProduto(String nome) {
        throw new RuntimeException("O produto " + nome + " não se encontra em estoque");
    }

    public RuntimeException lancaExcecaoVazio() {
        throw new RuntimeException("Estoque vazio, adicione um item primeiro");
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
            throw lancaExcecaoVazio();
        } else {
            for (Produto produto : produtos) {
                if (nome.equalsIgnoreCase(produto.getNome())) {
                    return produto;
                }
            }
            return null;
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
                throw new RuntimeException("Esse produto não existe");
            }
        }
    }
}
