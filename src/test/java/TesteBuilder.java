import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.ItemPedido;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;
import com.gqs.trabalhofinal_gqs.model.Produto;
import com.gqs.trabalhofinal_gqs.model.builder.CestaBasicaBuilder;
import com.gqs.trabalhofinal_gqs.model.builder.DiretorBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteBuilder {
    public TesteBuilder() {
    }

    private static ProdutosCollection estoque = ProdutosCollection.getInstancia();

    private Cliente cliente = new Cliente("Tarcisio");

    private Contexto pedido = new Contexto(LocalDateTime.now(), cliente);

    @BeforeAll
    public static void antes() {
        estoque.addProduto(new Produto("Carne", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Leite", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Ovo", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Arroz", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Feijão", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Canjica", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Trigo", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Açucar", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Cafe", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Oleo", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Maça", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Laranja", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Banana", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Arroz", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Feijao", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Manteiga", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Tomate", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Pao", 40, 30, "Comida"));
        estoque.addProduto(new Produto("Batata", 40, 30, "Comida"));

    }

    @Test
    @DisplayName("Cesta Básica economica")
    void CT001() {
        CestaBasicaBuilder builder = new CestaBasicaBuilder(new Cliente("Tarcisio"));
        DiretorBuilder diretor = new DiretorBuilder(builder);

        diretor.make("Economica");

        Contexto pedido = builder.getPedido();
        pedido.recalcularValores();
        //Total de produtos == R$ 2460
        //Impostos
        //ICMS = 2460 * 7% == 172,2
        //ISS = 2460 * 2% == 49,2
        //Tipo = 2460 * 4% == 98,4
        //Descontos
        //Tipo = 2460 * 2% == 49,2
        //Total = 2460 + 172,2 + 49,2 + 98,4 - 49,2 == 2730,6
        double precoEsperado = 2730.6;

        assertEquals(precoEsperado, pedido.getValorTotalAPagar(), 0.01);
    }

    @Test
    @DisplayName("Cesta Básica Premium")
    void CT002() {
        CestaBasicaBuilder builder = new CestaBasicaBuilder(new Cliente("Tarcisio"));
        DiretorBuilder diretor = new DiretorBuilder(builder);

        diretor.make("Premium");

        Contexto pedido = builder.getPedido();
        pedido.recalcularValores();

        //Total de produtos == 3120
        //Impostos
        //ICMS = 3120 * 7% == 218,4
        //ISS = 3120 * 2% == 62,4
        //Tipo = 3120 * 4% == 124,8
        //Descontos
        //Tipo = 3120 * 2% == 62,4
        //Total = 3120 + 218,4 + 62,4 + 124,8 - 62,4 ==

        double precoEsperado = 3463.2;

        assertEquals(precoEsperado, pedido.getValorTotalAPagar());
    }

}
