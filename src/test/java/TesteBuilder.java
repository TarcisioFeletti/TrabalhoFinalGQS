import com.gqs.trabalhofinal_gqs.collection.ProdutosCollection;
import com.gqs.trabalhofinal_gqs.model.Cliente;
import com.gqs.trabalhofinal_gqs.model.state.Contexto;
import com.gqs.trabalhofinal_gqs.model.Produto;
import com.gqs.trabalhofinal_gqs.model.builder.CestaBasicaBuilder;
import com.gqs.trabalhofinal_gqs.model.builder.DiretorBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TesteBuilder {
    public TesteBuilder() {
    }

    private static ProdutosCollection estoque = ProdutosCollection.getInstancia();

    private Cliente cliente = new Cliente("Tarcisio");

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
        CestaBasicaBuilder builder = new CestaBasicaBuilder(cliente);
        DiretorBuilder diretor = new DiretorBuilder(builder);

        diretor.make("Economica");

        Contexto pedido = builder.getPedido();
        pedido.recalcularValores();

        double precoEsperado = (5 * 30) + (3 * 30) + (12 * 30) + (10 * 30) + (5 * 30) + (3 * 30) + (3 * 30) + (5 * 30) + (3 * 30) + (3 * 30) + (6 * 30) + (12 * 30) + (12 * 30);

        assertEquals(precoEsperado, pedido.getValorTotalAPagar());
    }

    @Test
    @DisplayName("Cesta Básica Premium")
    void CT002() {
        CestaBasicaBuilder builder = new CestaBasicaBuilder(cliente);
        DiretorBuilder diretor = new DiretorBuilder(builder);

        diretor.make("Premium");

        Contexto pedido = builder.getPedido();
        pedido.recalcularValores();

        double precoEsperado = (5 * 30) + (3 * 30) + (12 * 30) + (10 * 30) + (5 * 30) + (3 * 30) + (3 * 30) + (5 * 30) + (3 * 30) + (3 * 30) + (6 * 30) + (12 * 30) + (12 * 30) + (30 * 22);

        assertEquals(precoEsperado, pedido.getValorTotalAPagar());
    }

}
