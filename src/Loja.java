import java.util.ArrayList;
import java.util.List;

public class Loja {
    List<Cliente> clientes = new ArrayList<>();
    List<Produto> estoque = new ArrayList<>();
    List<CarrinhoDeCompras> vendasFinalizadas = new ArrayList<>();
    CarrinhoDeCompras vendaEmAndamento;

    public void cadastrarCliente(Cliente novo) {
        clientes.add(novo);
    }

    public Cliente pesquisarCliente(String nome) {
        return clientes.stream().filter(c -> c.nome.equals(nome)).findFirst().orElse(null);
    }

    public void cadastrarProduto(Produto novo) {
        estoque.add(novo);
    }

    public Produto pesquisarProduto(int codigo) {
        return estoque.stream().filter(p -> p.codigo == codigo).findFirst().orElse(null);
    }

    public boolean iniciarVenda(String nomeCliente) {
        Cliente cliente = pesquisarCliente(nomeCliente);
        if (cliente != null) {
            vendaEmAndamento = new CarrinhoDeCompras(cliente);
            return true;
        }
        return false;
    }

    public boolean adicionarProduto(int codigo, int quantidade, double desconto) {
        Produto produto = pesquisarProduto(codigo);
        if (produto != null && vendaEmAndamento != null) {
            return vendaEmAndamento.adicionarProduto(produto, quantidade, desconto);
        }
        return false;
    }

    public double fecharVenda() {
        if (vendaEmAndamento != null) {
            double total = vendaEmAndamento.obterValorTotal();
            vendasFinalizadas.add(vendaEmAndamento);
            vendaEmAndamento = null;
            return total;
        }
        return 0;
    }
}
