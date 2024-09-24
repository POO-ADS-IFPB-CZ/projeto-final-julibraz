package view;

import model.Fornecedor;
import dao.GerenciaDao;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class TelaGerenciamentoFornecedores extends JFrame {
    private JTable tabelaFornecedores;
    private FornecedorTableModel fornecedorTableModel;
    private GerenciaDao<Fornecedor> fornecedorDao;
    public TelaGerenciamentoFornecedores() {
        fornecedorDao = new GerenciaDao<>("fornecedores.dat");
        fornecedorTableModel = new FornecedorTableModel();

        setTitle("Gerenciamento de Fornecedores");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tabelaFornecedores = new JTable(fornecedorTableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaFornecedores);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Fornecedor");
        JButton btnEditar = new JButton("Editar Fornecedor");
        JButton btnRemover = new JButton("Remover Fornecedor");
        JButton btnVoltar = new JButton("Voltar");

        panelButtons.add(btnAdicionar);
        panelButtons.add(btnEditar);
        panelButtons.add(btnRemover);
        panelButtons.add(btnVoltar);

        add(panelButtons, BorderLayout.SOUTH);


        carregarFornecedores();


        btnAdicionar.addActionListener(e -> {
            new TelaCadastroFornecedor(fornecedorTableModel, null, fornecedorDao).setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = tabelaFornecedores.getSelectedRow();
            if (selectedRow != -1) {
                Fornecedor fornecedor = fornecedorTableModel.getFornecedorAt(selectedRow);
                new TelaCadastroFornecedor(fornecedorTableModel, fornecedor, fornecedorDao).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um fornecedor para editar.");
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = tabelaFornecedores.getSelectedRow();
            if (selectedRow != -1) {
                Fornecedor fornecedor = fornecedorTableModel.getFornecedorAt(selectedRow);
                fornecedorTableModel.removerFornecedor(selectedRow);
                fornecedorDao.deletar(fornecedor);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um fornecedor para remover.");
            }
        });

        btnVoltar.addActionListener(e -> dispose()); // Fecha a tela
    }

    private void carregarFornecedores() {
        Set<Fornecedor> fornecedores = fornecedorDao.getAll();
        for (Fornecedor f : fornecedores) {
            fornecedorTableModel.adicionarFornecedor(f);
        }
    }
}
