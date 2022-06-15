import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.*;
import com.gqs.trabalhofinal_gqs.model.descontos.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


class TesteDesconto {
    private static Produto lapis = new Produto("Lapis", 10, 2.50,"papelaria");
    private static Produto mochila = new Produto("Mochila", 5, 100,"mochila");
    private static Produto caderno = new Produto("Caderno", 2, 10,"papelaria");

    private static Pedido pedido = new Pedido(LocalDateTime.now(), new Cliente("Tarcisio"));

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

    public TesteDesconto(){}

    @Test
    @DisplayName("Descontos básicos")
    void CT001(){
        /*Lápis custa 2.50 cada
        Total de lapis = 5
        Descontos = incentivo ->0,025, Tipo = 0,05 == 0,075
        lapis = 4,925

        Caderno = 10
        Descontos = incentivo -> 0,2, Tipo -> 0,1 == 0,3
        Caderno = 9,7

        Mochila = 100
        Descontos = Tipo -> 3
        Mochila = 97

        Valor = 97 + 9,7 + 4,925 + 3 = 114,625
        */

        double esperado = 115.075;
        pedido.addItem(item1, item2, item3);

        pedido.addImposto(ICMS);

        processadora.calculaDesconto();

        pedido.recalcularValores();

        assertEquals(esperado, pedido.getValorTotalAPagar());
    }

}
