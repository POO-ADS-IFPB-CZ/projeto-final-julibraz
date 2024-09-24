package view;

import model.Fornecedor;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class FornecedorTableModel extends AbstractTableModel {
    private List<Fornecedor> fornecedores;
    private String[] colunas = {"ID", "Nome", "CNPJ", "EMAIL"};

    public FornecedorTableModel() {
        fornecedores = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return fornecedores.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Fornecedor fornecedor = fornecedores.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return fornecedor.getId();
            case 1:
                return fornecedor.getNome();
            case 2:
                return fornecedor.getCnpj();
            case 3:
                return fornecedor.getEmail();
            default:
                return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public void adicionarFornecedor(Fornecedor fornecedor) {
        fornecedores.add(fornecedor);
        fireTableRowsInserted(fornecedores.size() - 1, fornecedores.size() - 1);
    }

    public void removerFornecedor(int rowIndex) {
        fornecedores.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public Fornecedor getFornecedorAt(int rowIndex) {
        return fornecedores.get(rowIndex);
    }

    public void atualizarTabela() {
        fireTableDataChanged();
    }
}
