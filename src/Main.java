import dao.GerenciaDao;
import model.Fornecedor;
import model.Produto;

import java.util.Set;

public class Main {
    public static void main(String[] args) {
        GerenciaDao<Produto> produtoDao = new GerenciaDao<>("produtos.ser");

        // Teste 1: Salvando um produto
        Produto produto1 = new Produto();
        produto1.setCodigo(1);
        produto1.setDescricao("Produto A");
        produto1.setValor(50.0);
        produto1.setQuantidade(20);

        boolean salvo = produtoDao.salvar(produto1);
        System.out.println("Produto salvo: " + salvo);

        // Teste 2: Carregando todos os produtos
        Set<Produto> produtos = produtoDao.getAll();
        System.out.println("Produtos carregados: " + produtos.size());
        produtos.forEach(p -> System.out.println(p.getDescricao()));

        // Teste 3: Atualizando o produto
        produto1.setDescricao("Produto A Atualizado");
        boolean atualizado = produtoDao.atualizar(produto1);
        System.out.println("Produto atualizado: " + atualizado);

        // Teste 4: Carregando produto atualizado
        produtos = produtoDao.getAll();
        produtos.forEach(p -> System.out.println(p.getDescricao()));

        // Teste 5: Deletando o produto
        boolean deletado = produtoDao.deletar(produto1);
        System.out.println("Produto deletado: " + deletado);

        // Teste 6: Carregando após deletar
        produtos = produtoDao.getAll();
        System.out.println("Produtos restantes após deleção: " + produtos.size());

        GerenciaDao<Fornecedor> fornecedorDao = new GerenciaDao<>("fornecedores.ser");

        // Teste 1: Salvando um fornecedor
        Fornecedor fornecedor1 = new Fornecedor();
        fornecedor1.setId(1);
        fornecedor1.setNome("Fornecedor X");

        boolean salvarFornedor = fornecedorDao.salvar(fornecedor1);
        System.out.println("Fornecedor salvo: " + salvarFornedor);

        // Teste 2: Carregando todos os fornecedores
        Set<Fornecedor> fornecedores = fornecedorDao.getAll();
        System.out.println("Fornecedores carregados: " + fornecedores.size());
        fornecedores.forEach(f -> System.out.println(f.getNome()));

        // Teste 3: Atualizando o fornecedor
        fornecedor1.setNome("Fornecedor X Atualizado");
        boolean atualizarFornecedor = fornecedorDao.atualizar(fornecedor1);
        System.out.println("Fornecedor atualizado: " + atualizarFornecedor);

        // Teste 4: Carregando fornecedor atualizado
        fornecedores = fornecedorDao.getAll();
        fornecedores.forEach(f -> System.out.println(f.getNome()));

        // Teste 5: Deletando o fornecedor
        boolean deletarFornecedor = fornecedorDao.deletar(fornecedor1);
        System.out.println("Fornecedor deletado: " + deletarFornecedor);

        // Teste 6: Carregando após deletar
        fornecedores = fornecedorDao.getAll();
        System.out.println("Fornecedores restantes após deleção: " + fornecedores.size());
    }
}
