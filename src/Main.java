
import model.Fornecedor;
import model.Produto;
import model.Usuario;
import controller.ProdutoRepository;

import java.util.Scanner;

public class Main {

    private static ProdutoRepository produtoRepo = new ProdutoRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha
            switch (opcao) {
                case 1 -> cadastrarFornecedor();
                case 2 -> cadastrarUsuario();
                case 3 -> cadastrarProduto();
                case 4 -> atualizarProduto();
                case 5 -> removerQuantidadeProduto();
                case 6 -> removerProduto();
                case 7 -> listarProdutos();
                case 8 -> verificarSenhaUsuario();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("===== Sistema de Controle de Estoque =====");
        System.out.println("1. Cadastrar Fornecedor");
        System.out.println("2. Cadastrar Usuário");
        System.out.println("3. Cadastrar Produto");
        System.out.println("4. Atualizar Produto");
        System.out.println("5. Remover Quantidade de Produto");
        System.out.println("6. Remover Produto");
        System.out.println("7. Listar Produtos");
        System.out.println("8. Verificar Senha de Usuário");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarFornecedor() {
        Fornecedor fornecedor = new Fornecedor();

        System.out.print("ID do Fornecedor: ");
        fornecedor.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.print("Nome do Fornecedor: ");
        fornecedor.setNome(scanner.nextLine());

        System.out.print("Email do Fornecedor: ");
        fornecedor.setEmail(scanner.nextLine());

        System.out.print("CNPJ do Fornecedor: ");
        fornecedor.setCnpj(scanner.nextLine());

        System.out.print("Contato do Fornecedor: ");
        fornecedor.setContato(scanner.nextLine());

        produtoRepo.adicionarFornecedor(fornecedor);
        System.out.println("Fornecedor cadastrado com sucesso!");
    }

    private static void cadastrarUsuario() {
        Usuario usuario = new Usuario();

        System.out.print("ID do Usuário: ");
        usuario.setId(scanner.nextLong());
        scanner.nextLine();

        System.out.print("Nome do Usuário: ");
        usuario.setNome(scanner.nextLine());

        System.out.print("Email do Usuário: ");
        usuario.setEmail(scanner.nextLine());

        System.out.print("CNPJ do Usuário: ");
        usuario.setCnpj(scanner.nextLine());

        System.out.print("Senha do Usuário: ");
        usuario.setSenha(scanner.nextLine());

        produtoRepo.adicionarUsuario(usuario);
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void cadastrarProduto() {
        Produto produto = new Produto();

        System.out.print("Código do Produto: ");
        produto.setCodigo(scanner.nextLong());
        scanner.nextLine();

        System.out.print("Nome do Produto: ");
        produto.setNome(scanner.nextLine());

        System.out.print("Descrição do Produto: ");
        produto.setDescricao(scanner.nextLine());

        System.out.print("Quantidade do Produto: ");
        produto.setQuantidade(scanner.nextInt());

        System.out.print("Valor do Produto: ");
        produto.setValor(scanner.nextDouble());
        scanner.nextLine();

        produtoRepo.salvar(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private static void atualizarProduto() {
        System.out.print("Código do Produto a atualizar: ");
        long codigo = scanner.nextLong();
        scanner.nextLine();

        Produto produto = produtoRepo.buscarPorCodigo(codigo);
        if (produto == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Novo Nome do Produto: ");
        produto.setNome(scanner.nextLine());

        System.out.print("Nova Descrição do Produto: ");
        produto.setDescricao(scanner.nextLine());

        System.out.print("Nova Quantidade do Produto: ");
        produto.setQuantidade(scanner.nextInt());

        System.out.print("Novo Valor do Produto: ");
        produto.setValor(scanner.nextDouble());
        scanner.nextLine();

        produtoRepo.atualizar(produto);
        System.out.println("Produto atualizado com sucesso!");
    }

    private static void removerQuantidadeProduto() {
        System.out.print("Código do Produto: ");
        long codigo = scanner.nextLong();

        System.out.print("Quantidade a remover: ");
        int quantidade = scanner.nextInt();

        produtoRepo.removerQuantidade(codigo, quantidade);
        System.out.println("Quantidade removida com sucesso!");
    }

    private static void removerProduto() {
        System.out.print("Código do Produto a remover: ");
        long codigo = scanner.nextLong();

        produtoRepo.removerProduto(codigo);
        System.out.println("Produto removido com sucesso!");
    }

    private static void listarProdutos() {
        System.out.println("Produtos no estoque:");
        for (Produto produto : produtoRepo.listarProdutos()) {
            System.out.println("Código: " + produto.getCodigo() +
                    ", Nome: " + produto.getNome() +
                    ", Quantidade: " + produto.getQuantidade() +
                    ", Valor: " + produto.getValor());
        }
    }

    private static void verificarSenhaUsuario() {
        System.out.print("ID do Usuário: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        Usuario usuario = produtoRepo.buscarUsuarioPorId(id);
        if (usuario == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        if (usuario.verificarSenha(senha)) {
            System.out.println("Senha correta!");
        } else {
            System.out.println("Senha incorreta.");
        }
    }
}
