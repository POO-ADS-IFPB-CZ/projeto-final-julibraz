package model;

import java.time.LocalDate;
import java.util.List;

public class Produto extends Base{
    private long codigo;
    private double valor;
    private String descricao;
    private int quantidade; //quantida em estoque do produto
    private LocalDate dataValidade;
    private List<Fornecedor> fornecedor;

    public Produto() {}

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
    public LocalDate getDataValidade() {
        return dataValidade;
    }
    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public List<Fornecedor> getFornecedor() {
        return fornecedor;
    }
    public void setFornecedor(List<Fornecedor> fornecedor) {
        this.fornecedor = fornecedor;
    }
}
