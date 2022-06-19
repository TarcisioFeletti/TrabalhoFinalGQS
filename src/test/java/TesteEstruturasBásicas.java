import com.gqs.trabalhofinal_gqs.collection.AvaliacoesCollection;
import com.gqs.trabalhofinal_gqs.collection.PedidosCollection;
import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.*;
import com.gqs.trabalhofinal_gqs.model.builder.CestaBasicaBuilder;
import com.gqs.trabalhofinal_gqs.model.builder.DiretorBuilder;
import com.gqs.trabalhofinal_gqs.model.chain.desconto.ProcessaDesconto;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TesteEstruturasBásicas {
    public TesteEstruturasBásicas() {
    }


    private static Cliente cliente = new Cliente("Tarcisio");

    private Avaliacao avaliacao = new Avaliacao(5, "Descrição generica", pedido);
    private static Produto lapis = new Produto("Lapis", 10, 2.50, "papelaria");

    private static Produto caderno = new Produto("Caderno", 5, 10, "Papelaria");
    private static Contexto pedido = new Contexto(LocalDateTime.now(), cliente);

    private static ItemPedido item = new ItemPedido(pedido, lapis, 2);
    private static ItemPedido item1 = new ItemPedido(pedido, caderno, 2);

    private ProcessaDesconto processadora = new ProcessaDesconto(pedido);

    @BeforeAll
    static void antes() {
        ProdutosCollection.getInstancia().addProduto(lapis);
        ProdutosCollection.getInstancia().addProduto(caderno);
    }

    @Test
    @DisplayName("Estruturas de Cliente")
    void CT001() {
        //Teste de propriedades
        assertThat(cliente, hasProperty("nome"));
        //Teste da criação do objeto
        assertThat(cliente, instanceOf(Cliente.class));
        //Teste dos métodos
        assertThat(cliente.getNome(), equalTo("Tarcisio"));
    }

    @Test
    @DisplayName("Estruturas de ItemPedido")
    void CT003() {
        //Teste de propriedades
        assertThat(item, hasProperty("pedido"));
        assertThat(item, hasProperty("item"));
        assertThat(item, hasProperty("quantidade"));
        assertThat(item, hasProperty("valorUnitario"));
        assertThat(item, hasProperty("valorTotal"));
        //Teste da criação do objeto
        assertThat(item, instanceOf(ItemPedido.class));
        //Teste dos métodos
        assertThat(item.getPedido(), allOf(is(pedido), instanceOf(Contexto.class)));
        assertThat(item.getItem(), allOf(is(lapis), instanceOf(Produto.class)));
        assertThat(item.getQuantidade(), equalTo(2));
        item.setQuantidade(3);
        assertThat(item.getQuantidade(), equalTo(3));
        assertThat(item.getValorUnitario(), equalTo(2.50));
        assertThat(item.getValorTotal(), equalTo(5.0));
    }

    @Test
    @DisplayName("Estruturas de Contexto")
    void CT004() {
        //Teste de propriedades
        assertThat(pedido, hasProperty("numero"));
        assertThat(pedido, hasProperty("data"));
        assertThat(pedido, hasProperty("valor"));
        assertThat(pedido, hasProperty("valorTotalImpostos"));
        assertThat(pedido, hasProperty("valorTotalAPagar"));
        assertThat(pedido, hasProperty("valorTotalDescontos"));
        assertThat(pedido, hasProperty("produtos"));
        assertThat(pedido, hasProperty("cliente"));
        //Teste da criação do objeto
        assertThat(pedido, instanceOf(Contexto.class));
        //Teste dos métodos
        assertThat(pedido.getData(), instanceOf(LocalDateTime.class));
        assertThat(pedido.getProdutos(), instanceOf(List.class));
        assertThat(pedido.getCliente(), instanceOf(Cliente.class));
        assertThat(pedido.getValor(), equalTo(0.0));
        assertThat(pedido.getValorTotalImpostos(), equalTo(0.0));
        assertThat(pedido.getValorTotalDescontos(), equalTo(0.0));
        assertThat(pedido.getValorTotalAPagar(), equalTo(0.0));

        pedido.addItem(item1);
        assertThat(pedido.getProdutos().size(), equalTo(1));
        pedido.removerItens(item1);
        assertThat(pedido.getProdutos().size(), equalTo(0));
        pedido.addItem(item1);
        try {
            pedido.removerItens(new ItemPedido(pedido, caderno, 100));
        }catch(RuntimeException e){
            assertThat(e, instanceOf(RuntimeException.class));
            assertThat(e.getMessage(), equalTo("Quantidade não suportada"));
        }
        pedido.addItem(item1);
        pedido.removerItens(new ItemPedido(pedido, caderno, 1));
        assertThat(pedido.getProdutos().size(), equalTo(1));
        //pedido.addItem(item1);
    }

    @Test
    @DisplayName("Estruturas de Produto")
    void CT005() {
        //Teste de propriedades
        assertThat(lapis, hasProperty("nome"));
        assertThat(lapis, hasProperty("quantidadeEmEstoque"));
        assertThat(lapis, hasProperty("precoUnitario"));
        assertThat(lapis, hasProperty("tipo"));
        //Teste da criação do objeto
        assertThat(lapis, instanceOf(Produto.class));
        //Teste dos métodos
        assertThat(lapis.getNome(), equalTo("Lapis"));
        assertThat(lapis.getTipo(), equalTo("papelaria"));
        assertThat(lapis.getPrecoUnitario(), equalTo(2.5));
        assertThat(lapis.getQuantidadeEmEstoque(), equalTo(10));
        try{
            lapis.vender(200);
        }catch(RuntimeException e){
            assertThat(e, instanceOf(RuntimeException.class));
            assertThat(e.getMessage(), equalTo("Quantidade não disponível"));
        }
    }

    @Test
    @DisplayName("Estruturas de Avaliação")
    void CT006() {
        //Teste de propriedades
        assertThat(avaliacao, hasProperty("nota"));
        assertThat(avaliacao, hasProperty("descricao"));
        //Teste da criação do objeto
        assertThat(avaliacao, instanceOf(Avaliacao.class));
        //Teste dos métodos
        assertThat(avaliacao.getNota(), equalTo(5));
        assertThat(avaliacao.getDescricao(), equalTo("Descrição generica"));
        assertThat(avaliacao.getPedido(), samePropertyValuesAs(pedido));
    }

    @Test
    @DisplayName("Estruturas de Avaliação Collection")
    void CT007() {
        Avaliacao avalia = new Avaliacao(avaliacao);
        AvaliacoesCollection avaliacoes = AvaliacoesCollection.getInstancia();
        avaliacoes.addAvaliacao(avalia);
        //Teste de propriedades
        assertThat(avaliacoes, hasProperty("avaliacoes"));
        //Teste da criação do objeto
        assertThat(avaliacoes, instanceOf(AvaliacoesCollection.class));
        //Teste dos métodos
        assertThat(avaliacoes, samePropertyValuesAs(AvaliacoesCollection.getInstancia()));
        assertThat(avaliacoes.getAvaliacoes(), hasItem(avalia));
    }

    @Test
    @DisplayName("Estruturas de Pedidos Collection")
    void CT008() {
        PedidosCollection pedidos = PedidosCollection.getInstancia();
        //Teste da criação do objeto
        assertThat(pedidos, instanceOf(PedidosCollection.class));
        //Teste dos métodos
        try {
            pedidos.getPedido(2);
        } catch (RuntimeException e) {
            assertThat(e, instanceOf(RuntimeException.class));
            assertThat(e.getMessage(), equalTo("Não existe nenhum pedido"));
        }
        pedidos.addPedido(pedido);
        assertThat(pedidos.getAll(), hasItem(pedido));
        assertThat(pedidos.getPedido(pedido.getNumero()), samePropertyValuesAs(pedido));

        try {
            pedidos.getPedido(2);
        } catch (RuntimeException e) {
            assertThat(e, instanceOf(RuntimeException.class));
            assertThat(e.getMessage(), equalTo("Esse pedido não está listado"));
        }
    }

}
