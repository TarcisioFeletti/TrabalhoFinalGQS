import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.*;
import com.gqs.trabalhofinal_gqs.model.descontos.ProcessaDesconto;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class TesteEstruturasBásicas {
    public TesteEstruturasBásicas() {
    }


    private Cliente cliente = new Cliente("Tarcisio");
    private Produto lapis = new Produto("Lapis", 10, 2.50,"papelaria");

    private Pedido pedido = new Pedido(LocalDateTime.now(), cliente);

    private ItemPedido item = new ItemPedido(pedido, lapis, 2);;

    private Imposto imposto = new Imposto("icms", 3);

    private ProcessaDesconto processadora = new ProcessaDesconto(pedido);

    @Test
    @DisplayName("Estruturas de Cliente")
    void CT001(){
        //Teste de propriedades
        assertThat(cliente, hasProperty("nome"));
        //Teste da criação do objeto
        assertThat(cliente, instanceOf(Cliente.class));
        //Teste dos métodos
        assertThat(cliente.getNome(), equalTo("Tarcisio"));
    }

    @Test
    @DisplayName("Estruturas de Imposto")
    void CT002(){
        //Teste de propriedades
        assertThat(imposto, hasProperty("nome"));
        assertThat(imposto, hasProperty("percentual"));
        //Teste da criação do objeto
        assertThat(imposto, instanceOf(Imposto.class));
        //Teste dos métodos
        assertThat(imposto.getNome(), equalTo("icms"));
        assertThat(imposto.getPercentual(), equalTo(3.0));
    }

    @Test
    @DisplayName("Estruturas de ItemPedido")
    void CT003(){
        //Teste de propriedades
        assertThat(item, hasProperty("pedido"));
        assertThat(item, hasProperty("item"));
        assertThat(item, hasProperty("quantidade"));
        assertThat(item, hasProperty("valorUnitario"));
        assertThat(item, hasProperty("valorTotal"));
        //Teste da criação do objeto
        assertThat(item, instanceOf(ItemPedido.class));
        //Teste dos métodos
        assertThat(item.getPedido(), allOf(is(pedido), instanceOf(Pedido.class)));
        assertThat(item.getItem(), allOf(is(lapis), instanceOf(Produto.class)));
        assertThat(item.getQuantidade(), equalTo(2));
        assertThat(item.getValorUnitario(), equalTo(2.50));
        assertThat(item.getValorTotal(), equalTo(5.0));
    }

    @Test
    @DisplayName("Estruturas de Pedido")
    void CT004(){
        //Teste de propriedades
        assertThat(pedido, hasProperty("numero"));
        assertThat(pedido, hasProperty("data"));
        assertThat(pedido, hasProperty("valor"));
        assertThat(pedido, hasProperty("valorTotalImpostos"));
        assertThat(pedido, hasProperty("valorTotalAPagar"));
        assertThat(pedido, hasProperty("valorTotalDescontos"));
        assertThat(pedido, hasProperty("produtos"));
        assertThat(pedido, hasProperty("impostos"));
        assertThat(pedido, hasProperty("cliente"));
        //Teste da criação do objeto
        assertThat(pedido, instanceOf(Pedido.class));
        //Teste dos métodos
        assertThat(pedido.getData(), instanceOf(LocalDateTime.class));
        assertThat(pedido.getProdutos(), instanceOf(List.class));
        assertThat(pedido.getImpostos(), instanceOf(List.class));
        assertThat(pedido.getCliente(), instanceOf(Cliente.class));
        assertThat(pedido.getValor(), equalTo(0.0));
        assertThat(pedido.getValorTotalImpostos(), equalTo(0.0));
        assertThat(pedido.getValorTotalDescontos(), equalTo(0.0));
        assertThat(pedido.getValorTotalAPagar(), equalTo(0.0));
    }

    @Test
    @DisplayName("Estruturas de Produto")
    void CT005(){
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
    }

}
