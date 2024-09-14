package model;

import controller.ProdutoFornecedor;
import exceptions.ProdutoNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Fornecedor extends Base{

    private List<ProdutoFornecedor> estoque;

    public Fornecedor() {
        estoque = new ArrayList<>();
    }

    public List<ProdutoFornecedor> getEstoque() {
        return estoque;
    }

    public void adicionarProdutoFornecedor(ProdutoFornecedor produtoFornecedor) {
        estoque.add(produtoFornecedor);
    }

    //faz a busca de um produto pelo seu código
    public ProdutoFornecedor buscarProdutoPorCodigo(long codigo) {
        return estoque.stream()
                .filter(produto -> produto.getProduto().getCodigo() == codigo)
                .findFirst()
                .orElseThrow(() -> new ProdutoNaoEncontradoException(codigo));
    }

    //atualiza um estoque
    public void atualizarProduto(Produto produto, double preco, int quantidade) {
        ProdutoFornecedor produtoFornecedor = buscarProdutoPorCodigo(produto.getCodigo());
        if(produtoFornecedor != null) {
            produtoFornecedor.setPreco(preco);
            produtoFornecedor.setQuantidade(quantidade);
        }
    }

    public void removerProduto(long codigoProduto) {
        estoque.removeIf(produtoFornecedor -> produtoFornecedor.getProduto().getCodigo() == codigoProduto);
    }



}
