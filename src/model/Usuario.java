package model;

import controller.ProdutoFornecedor;
import exceptions.ProdutoNaoEncontradoException;
import exceptions.QuantidadeInsuficienteException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Usuario extends Base {

    private List<Produto> estoqueUsuario;

    public Usuario() {
        estoqueUsuario = new ArrayList<>();
    }

    //adiciona produtos ao estoque da empresa
    public void adicionarProduto(Produto produto, int quantidade) {
        Produto existente = buscarProdutoPorCodigo(produto.getCodigo());
        if (existente != null) {
            existente.setQuantidade(existente.getQuantidade() + quantidade);
        } else {
            produto.setQuantidade(quantidade);
            estoqueUsuario.add(produto);
        }
    }

    //remove um estoque por completo
    public void removerProduto(long codigo) {
        estoqueUsuario.removeIf(produto -> produto.getCodigo() == codigo);
    }

    //faz a busca de um estoque pelo seu código
    public Produto buscarProdutoPorCodigo(long codigo) {
        try {
            return estoqueUsuario.stream()
                    .filter(produto -> produto.getCodigo() == codigo)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Produto com código " + codigo + " não encontrado"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //atualiza um estoque
    public void solicitarProduto(Fornecedor fornecedor, long codigo, int quantidade) {
        ProdutoFornecedor produtoFornecedor = fornecedor.buscarProdutoPorCodigo(codigo);
        if (produtoFornecedor != null && produtoFornecedor.getQuantidade() >= quantidade) {
            produtoFornecedor.setQuantidade(produtoFornecedor.getQuantidade() - quantidade);
            adicionarProduto(produtoFornecedor.getProduto(), quantidade);
            System.out.println("Produto solicitado com sucesso!");
        } else {
            System.out.println("Quantidade insuficiente ou produto não disponível.");
        }

    }

    //faz a remoção de um produto dependedo da quantidade especificada
    public void removerQuantidade(long codigo, int quantidade) {
        Produto produto = buscarProdutoPorCodigo(codigo);
        if (produto != null) {
            int quantidadeAtual = produto.getQuantidade();
            if (quantidadeAtual >= quantidade) {
                produto.setQuantidade(quantidadeAtual - quantidade);
            } else {
                throw new QuantidadeInsuficienteException(quantidadeAtual, quantidade);
            }
        }else {
            throw new ProdutoNaoEncontradoException(codigo);
        }
    }

}
