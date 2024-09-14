
import controller.ProdutoFornecedor;
import controller.ProdutoRepository;
import model.Fornecedor;
import model.Produto;
import model.Usuario;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.QuantidadeInsuficienteException;

public class Main {
    public static void main(String[] args) {
        // Criação de instâncias
        ProdutoRepository repo = new ProdutoRepository();

        // Criação de fornecedores
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setId(1);
        fornecedor1.setNome("Fornecedor A");
        fornecedor1.setEmail("fornecedorA@example.com");
        fornecedor1.setCnpj("12.345.678/0001-95");

        Fornecedor fornecedor2 = new Fornecedor();
        fornecedor2.setId(2);
        fornecedor2.setNome("Fornecedor B");
        fornecedor2.setEmail("fornecedorB@example.com");
        fornecedor2.setCnpj("98.765.432/0001-10");

        // Adicionando fornecedores ao repositório
        repo.adicionarFornecedor(fornecedor1);
        repo.adicionarFornecedor(fornecedor2);

        // Criação de produtos
        Produto produto1 = new Produto();
        produto1.setCodigo(1001);
        produto1.setNome("Produto 1");
        produto1.setDescricao("Descrição do Produto 1");
        produto1.setValor(10.0);
        produto1.setQuantidade(50);

        Produto produto2 = new Produto();
        produto2.setCodigo(1002);
        produto2.setNome("Produto 2");
        produto2.setDescricao("Descrição do Produto 2");
        produto2.setValor(20.0);
        produto2.setQuantidade(30);

        // Adicionando produtos ao repositório
        repo.salvar(produto1);
        repo.salvar(produto2);

        // Criando instâncias de ProdutoFornecedor
        ProdutoFornecedor pf1 = new ProdutoFornecedor(fornecedor1, produto1, 50, 10.0);
        ProdutoFornecedor pf2 = new ProdutoFornecedor(fornecedor2, produto2, 30, 20.0);

        // Adicionando produtos ao estoque dos fornecedores
        fornecedor1.adicionarProdutoFornecedor(pf1);
        fornecedor2.adicionarProdutoFornecedor(pf2);

        // Criação de usuário
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Usuário A");
        usuario.setEmail("usuarioA@example.com");
        usuario.setCnpj("12.345.678/0001-95");

        // Adicionando produtos ao estoque do usuário
        usuario.adicionarProduto(produto1, 10);
        usuario.adicionarProduto(produto2, 5);

        // Teste de métodos
        System.out.println("Estoque do Usuário:");
        usuario.getEstoqueUsuario().forEach(prod ->
                System.out.println(prod.getNome() + " - Quantidade: " + prod.getQuantidade()));

        // Testando solicitação de produtos
        System.out.println("\nSolicitando produtos...");
        try {
            usuario.solicitarProduto(fornecedor1, 1001, 5);
            usuario.solicitarProduto(fornecedor2, 1002, 5);
        } catch (QuantidadeInsuficienteException | ProdutoNaoEncontradoException e) {
            System.out.println(e.getMessage());
        }

        // Testando remoção de produtos
        System.out.println("\nRemovendo produtos...");
        usuario.removerProduto(1001);
        fornecedor1.removerProduto(1001);

        // Verificando o resultado das operações
        System.out.println("\nEstoque do Usuário após remoção:");
        usuario.getEstoqueUsuario().forEach(prod ->
                System.out.println(prod.getNome() + " - Quantidade: " + prod.getQuantidade()));

        System.out.println("\nEstoque do Fornecedor 1 após remoção:");
        fornecedor1.getEstoque().forEach(pf ->
                System.out.println(pf.getProduto().getNome() + " - Quantidade: " + pf.getQuantidade()));
    }
}
