public class Produto {
    int codigo;
    double valor;

    public Produto(int codigo, double valor) {
        this.codigo = codigo;
        this.valor = valor;
    }

    public double obterValorComDesconto(double percentual) {
        return valor - (valor * percentual / 100);
    }
}
