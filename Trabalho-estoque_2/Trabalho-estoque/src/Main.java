import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ControleEstoque controle = new ControleEstoque();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\n===== SISTEMA DE ESTOQUE =====");
            System.out.println("1. Adicionar produtos");
            System.out.println("2. Atualizar a quantidade de um produto");
            System.out.println("3. Atualizar o preço de um produto");
            System.out.println("4. Listar estoque");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do produto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Quantidade inicial: ");
                    int qtd = scanner.nextInt();
                    System.out.print("Preço: ");
                    double preco = scanner.nextDouble();

                    Produto novoProduto = new Produto(nome, qtd, preco);
                    controle.adicionarProduto(novoProduto);
                    break;

                case 2:
                    System.out.print("Digite o nome do produto que deseja atualizar: ");
                    String nomeBusca = scanner.nextLine();
                    System.out.print("Digite a nova quantidade do produto: ");
                    int novaQtd = scanner.nextInt();

                    controle.atualizarQuantidade(nomeBusca, novaQtd);
                    break;

                case 3:
                    System.out.print("Digite o nome do produto que deseja atualizar: ");
                    String nomeBusca_2 = scanner.nextLine();
                    System.out.println("Informe o novo preço do produto: ");
                    double novoPrc = scanner.nextDouble();

                    controle.atualizarPreco(nomeBusca_2, novoPrc);
                    break;

                case 4:
                    controle.listarProdutos();
                    break;

                case 0:
                    System.out.println("Saindo do sistema...");
                    break;

                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 0);

        scanner.close();
    }
}