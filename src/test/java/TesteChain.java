import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.*;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TesteChain {
    private static Produto lapis = new Produto("Lapis", 10, 2.50,"papelaria");
    private static Produto mochila = new Produto("Mochila", 5, 100,"mochila");
    private static Produto caderno = new Produto("Caderno", 2, 10,"caderno");
    private static Produto borracha = new Produto("Borracha", 2, 10,"borracha");

    private static Contexto pedido = new Contexto(LocalDateTime.now(), new Cliente("Tarcisio"));

    private static ItemPedido item1 = new ItemPedido(pedido, lapis, 2);
    private static ItemPedido item2 = new ItemPedido(pedido, mochila, 1);
    private static ItemPedido item3 = new ItemPedido(pedido, caderno, 1);
    private static ItemPedido item4 = new ItemPedido(pedido, borracha, 1);

    @BeforeAll
    public static void antes(){
        ProdutosCollection.getInstancia().addProduto(lapis);
        ProdutosCollection.getInstancia().addProduto(mochila);
        ProdutosCollection.getInstancia().addProduto(caderno);
        ProdutosCollection.getInstancia().addProduto(borracha);
    }

    public TesteChain(){}

    @Test
    @DisplayName("Descontos básicos")
    void CT001(){
        /*Lápis custa 2.50 cada
        Total de lapis = 5
        Descontos = incentivo ->0,025, Tipo -> 0,05 == 0,075
        Impostos = ICMS -> 0,35, ISS -> 0,1, Tipo -> 0,25 == 0,7

        Caderno = 10
        Descontos = incentivo -> 0,2 == 0,2
        Impostos = ICMS -> 0,7, ISS -> 0,2, Tipo -> 0,8 == 1,7

        Mochila = 100
        Descontos = Tipo -> 3
        Impostos = ICMS -> 7, ISS -> 2, Tipo -> 8 == 17

        Borracha = 10
        Descontos = Incentico -> 0,1
        IMpostos = ICMS -> 0,7, ISS -> 0,2, Tipo -> 0,8 == 1,7

        Valor = 125 + 21,1 - 3,375 =
        */

        double esperado = 142.725;
        pedido.addItem(item1, item2, item3, item4);

        pedido.recalcularValores();

        assertEquals(esperado, pedido.getValorTotalAPagar());
    }

}
