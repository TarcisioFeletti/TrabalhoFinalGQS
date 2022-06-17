import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.Imposto;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.Produto;
import com.gqs.trabalhofinal_gqs.model.descontos.ProcessaDesconto;
import com.gqs.trabalhofinal_gqs.model.state.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;

public class TesteState {
    private static Cliente cliente = new Cliente("Tarcisio");
    private static Contexto pedido = new Contexto(LocalDateTime.now(),cliente);
    private static Produto lapis = new Produto("Lapis", 100, 2.50,"papelaria");
    private static Produto mochila = new Produto("Mochila", 20, 100,"mochila");
    private static Produto caderno = new Produto("Caderno", 20, 10,"papelaria");
    private static ItemPedido item1 = new ItemPedido(pedido, lapis, 2);
    private static ItemPedido item2 = new ItemPedido(pedido, mochila, 1);
    private static ItemPedido item3 = new ItemPedido(pedido, caderno, 1);

    private static Imposto ICMS = new Imposto("icms", 3);

    private static ProcessaDesconto processadora = new ProcessaDesconto(pedido);

    @BeforeAll
    public static void antes(){
        ProdutosCollection.getInstancia().addProduto(lapis);
        ProdutosCollection.getInstancia().addProduto(mochila);
        ProdutosCollection.getInstancia().addProduto(caderno);
    }

    @BeforeEach
    public void antesDeCada(){
        pedido = new Contexto(LocalDateTime.now(),cliente);

    }

    @Test
    @DisplayName("Entregar o pedido")
    void CT001(){
        pedido.addItem(item1,item2,item3);
        pedido.addImposto(ICMS);
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(EmRotaDeEntregaState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(EntregueState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido esta pronto para entrega")
    void CT002(){
        pedido.addItem(item1,item2,item3);
        pedido.addImposto(ICMS);
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
        pedido.getEstado().cancelar();
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido esta confirmado")
    void CT003(){
        pedido.addItem(item1,item2,item3);
        pedido.addImposto(ICMS);
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
        pedido.getEstado().cancelar();
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido esta aguardando pagamento")
    void CT004(){
        pedido.addItem(item1,item2,item3);
        pedido.addImposto(ICMS);
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.getEstado().cancelar();
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloClienteState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido é um novo pedido")
    void CT005(){
        pedido.addItem(item1,item2,item3);
        pedido.addImposto(ICMS);
        pedido.getEstado().cancelar();
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloClienteState.class));
        pedido.getEstado().avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }
}
