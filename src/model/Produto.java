package model;

import java.util.ArrayList;
import java.util.List;

public class Produto extends Base{
    private long codigo;
    private double valor;
    private String descricao;
    private int quantidade; //quantida em estoque do produto
    private List<Fornecedor> fornecedores;

    public Produto() {
        fornecedores = new ArrayList<>();
    }

    public long getCodigo() {
        return codigo;
    }
    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public List<Fornecedor> getFornecedor() {
        return fornecedores;
    }
    public void setFornecedor(List<Fornecedor> fornecedor) {
        this.fornecedores = fornecedor;
    }

    //vincula um produto a um fornecedor
    public void adicionarFornecedor(Fornecedor fornecedor) {
        if(!fornecedores.contains(fornecedor)){
            fornecedores.add(fornecedor);
            fornecedor.adicionarProduto(this);
        }
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        if(fornecedores.contains(fornecedor)){
            fornecedores.remove(fornecedor);
            fornecedor.removerProduto(this.getCodigo());
        }
    }
}
