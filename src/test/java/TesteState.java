import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.Produto;
import com.gqs.trabalhofinal_gqs.model.chain.desconto.ProcessaDesconto;
import com.gqs.trabalhofinal_gqs.model.state.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class TesteState {
    private static Cliente cliente = new Cliente("Tarcisio");
    private static Contexto pedido = new Contexto(LocalDateTime.now(),cliente);
    private static Produto lapis = new Produto("Lapis", 500, 2.50,"papelaria");
    private static Produto mochila = new Produto("Mochila", 100, 100,"mochila");
    private static Produto caderno = new Produto("Caderno", 100, 10,"papelaria");
    private static ItemPedido item1 = new ItemPedido(pedido, lapis, 2);
    private static ItemPedido item2 = new ItemPedido(pedido, mochila, 1);
    private static ItemPedido item3 = new ItemPedido(pedido, caderno, 1);
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
        pedido.addItem(item1,item2,item3);
    }

    @Test
    @DisplayName("Entregar o pedido")
    void CT001(){
        assertThat(pedido.toString(), equalToIgnoringCase("Novo pedido"));
        pedido.avancar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido aguardando pagamento"));
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.avancar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido confirmado"));
        assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
        pedido.avancar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido pronto para entrega"));
        assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
        pedido.avancar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido em rota de entrega"));
        assertThat(pedido.getEstado(), instanceOf(EmRotaDeEntregaState.class));
        pedido.avancar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido entregue"));
        assertThat(pedido.getEstado(), instanceOf(EntregueState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido esta pronto para entrega")
    void CT002(){
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
        pedido.cancelar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido cancelado pelo estabelecimento"));
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
        pedido.avancar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido reembolsado"));
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido esta confirmado")
    void CT003(){
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
        pedido.cancelar();
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido esta aguardando pagamento")
    void CT004(){
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
        pedido.cancelar();
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloClienteState.class));
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar quando o pedido é um novo pedido")
    void CT005(){
        pedido.cancelar();
        assertThat(pedido.toString(), equalToIgnoringCase("Pedido cancelado pelo cliente"));
        assertThat(pedido.getEstado(), instanceOf(CanceladoPeloClienteState.class));
        pedido.avancar();
        assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
    }

    @Test
    @DisplayName("Cancelar um pedido cancelado pelo cliente")
    void CT006(){
        try{
            pedido.cancelar();
            assertThat(pedido.getEstado(), instanceOf(CanceladoPeloClienteState.class));
            pedido.cancelar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    @Test
    @DisplayName("Cancelar um pedido em rota de entrega")
    void CT007(){
        try{
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(EmRotaDeEntregaState.class));
            pedido.cancelar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    @Test
    @DisplayName("Cancelar um pedido entregue")
    void CT008(){
        try{
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(EmRotaDeEntregaState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(EntregueState.class));
            pedido.cancelar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    @Test
    @DisplayName("Cancelar um pedido cancelado pelo estabelecimento")
    void CT009(){
        try{
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
            pedido.cancelar();
            assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
            pedido.cancelar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    @Test
    @DisplayName("Cancelar um pedido reembolsado")
    void CT010(){
        try{
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
            pedido.cancelar();
            assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
            pedido.cancelar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    @Test
    @DisplayName("Avançar um pedido entregue")
    void CT011(){
        try{
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ProntoParaEntregaState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(EmRotaDeEntregaState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(EntregueState.class));
            pedido.avancar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }

    @Test
    @DisplayName("Avançar um pedido reembolsado")
    void CT012(){
        try{
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(AguardandoPagamentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ConfirmadoState.class));
            pedido.cancelar();
            assertThat(pedido.getEstado(), instanceOf(CanceladoPeloEstabelecimentoState.class));
            pedido.avancar();
            assertThat(pedido.getEstado(), instanceOf(ReembolsadoState.class));
            pedido.avancar();
        }catch(Exception e){
            assertThat(e, instanceOf(UnsupportedOperationException.class));
        }

    }
}
