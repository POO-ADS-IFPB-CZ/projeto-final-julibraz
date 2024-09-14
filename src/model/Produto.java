package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
        //verifica se o codigo do produto é maior que zero
        if (codigo > 0) {
            this.codigo = codigo;
        } else {
            throw new IllegalArgumentException("O código do produto deve ser maior que zero");
        }
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        //garante que o valor do produto não seja negativo
        if (valor >= 0) {
            this.valor = valor;
        } else {
            throw new IllegalArgumentException("O valor do produto não pode ser negativo");
        }
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
        }
    }

    public void removerFornecedor(Fornecedor fornecedor) {
        if (fornecedores.contains(fornecedor)) {
            fornecedores.remove(fornecedor);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return codigo == produto.codigo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}
