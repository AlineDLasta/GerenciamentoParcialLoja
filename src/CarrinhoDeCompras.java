import java.util.ArrayList;
import java.util.List;

public class CarrinhoDeCompras {
    List<ItemCarrinho> items = new ArrayList<>();
    Cliente cliente;

    public CarrinhoDeCompras(Cliente cliente) {
        this.cliente = cliente;
    }

    public boolean adicionarProduto(Produto produto, int quantidade, double desconto) {
        items.add(new ItemCarrinho(produto, quantidade, desconto));
        return true;
    }

    public double obterValorTotal() {
        return items.stream().mapToDouble(ItemCarrinho::obterCusto).sum();
    }
}
