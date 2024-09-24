package view;

import model.Produto;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProdutoTableModel extends AbstractTableModel {
    private List<Produto> produtos;
    private String[] colunas = {"ID", "Código", "Descrição", "Valor", "Quantidade"};

    public ProdutoTableModel() {
        produtos = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return produtos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Produto produto = produtos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return produto.getId();
            case 1:
                return produto.getCodigo();
            case 2:
                return produto.getDescricao();
            case 3:
                return produto.getValor();
            case 4:
                return produto.getQuantidade();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
        fireTableRowsInserted(produtos.size() - 1, produtos.size() - 1);
    }

    public void removerProduto(int rowIndex) {
        produtos.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Produto getProdutoAt(int rowIndex) {
        return produtos.get(rowIndex);
    }

    public void atualizarTabela() {
        fireTableDataChanged();
    }
}
