package model;

import controller.ProdutoFornecedor;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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

    //faz a busca de um estoque pelo seu código
    public ProdutoFornecedor buscarProdutoPorCodigo(long codigo) {
        try {
            return estoque.stream()
                    .filter(produto -> produto.getProduto().getCodigo() == codigo)
                    .findFirst()
                    .orElseThrow(() -> new NoSuchElementException("Produto com código " + codigo + " não encontrado"));
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    //atualiza um estoque
    public void atualizarProduto(Produto produto, double preco, int quantidade) {
        ProdutoFornecedor produtoFornecedor = buscarProdutoPorCodigo(produto.getCodigo());
        if(produtoFornecedor != null) {
            produtoFornecedor.setPreco(preco);
            produtoFornecedor.setQuantidadeDisponivel(quantidade);
        }
    }


}
