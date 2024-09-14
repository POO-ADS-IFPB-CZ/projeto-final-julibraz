package controller;


import model.Fornecedor;
import model.Produto;

//Faz e mantema a relação entre o estoque e o fornecedor
public class ProdutoFornecedor {
    private Fornecedor fornecedor;
    private Produto produto;
    private int quantidade;
    private double preco;

    public ProdutoFornecedor(Fornecedor fornecedor, Produto produto, int quantidade, double preco) {
        this.fornecedor = fornecedor;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
