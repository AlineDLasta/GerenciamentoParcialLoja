import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Loja loja = new Loja();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n=== Gerenciamento Parcial da Loja ===");
            System.out.println("1 - Cadastrar cliente");
            System.out.println("2 - Cadastrar produto");
            System.out.println("3 - Iniciar venda");
            System.out.println("4 - Adicionar produto ao carrinho");
            System.out.println("5 - Exibir conteúdo do carrinho");
            System.out.println("6 - Fechar venda");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir quebra de linha

            switch (opcao) {
                case 1 -> {
                    System.out.print("Nome do cliente: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF do cliente: ");
                    long cpf = scanner.nextLong();
                    loja.cadastrarCliente(new Cliente(nome, cpf));
                    System.out.println("Cliente cadastrado com sucesso!");
                }
                case 2 -> {
                    System.out.print("Código do produto: ");
                    int codigo = scanner.nextInt();
                    System.out.print("Valor do produto: ");
                    double valor = scanner.nextDouble();
                    loja.cadastrarProduto(new Produto(codigo, valor));
                    System.out.println("Produto cadastrado com sucesso!");
                }
                case 3 -> {
                    System.out.print("Nome do cliente para iniciar venda: ");
                    String nomeCliente = scanner.nextLine();
                    if (loja.iniciarVenda(nomeCliente)) {
                        System.out.println("Venda iniciada com sucesso!");
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("Código do produto: ");
                    int codigo = scanner.nextInt();
                    System.out.print("Quantidade: ");
                    int quantidade = scanner.nextInt();
                    System.out.print("Desconto (%): ");
                    double desconto = scanner.nextDouble();
                    if (loja.adicionarProduto(codigo, quantidade, desconto)) {
                        System.out.println("Produto adicionado ao carrinho!");
                    } else {
                        System.out.println("Erro ao adicionar produto.");
                    }
                }
                case 5 -> {
                    if (loja.vendaEmAndamento != null) {
                        System.out.println("Conteúdo do Carrinho:");
                        for (ItemCarrinho item : loja.vendaEmAndamento.items) {
                            System.out.println("- Produto: " + item.produto.codigo +
                                    " | Qtd: " + item.quantidade +
                                    " | Valor unitário: " + item.produto.valor +
                                    " | Desconto: " + item.desconto + "%" +
                                    " | Subtotal: " + item.obterCusto());
                        }
                        System.out.println("Total: R$ " + loja.vendaEmAndamento.obterValorTotal());
                    } else {
                        System.out.println("Nenhuma venda em andamento.");
                    }
                }
                case 6 -> {
                    double total = loja.fecharVenda();
                    if (total > 0) {
                        System.out.println("Venda fechada com sucesso! Total: R$ " + total);
                    } else {
                        System.out.println("Nenhuma venda em andamento para fechar.");
                    }
                }
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}
