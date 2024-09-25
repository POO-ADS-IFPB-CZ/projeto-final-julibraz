package view;

import model.Produto;
import dao.GerenciaDao;

import javax.swing.*;
import java.awt.*;
import java.util.Set;

public class TelaGerenciamentoProdutos extends JFrame {
    private JTable tabelaProdutos;
    private ProdutoTableModel produtoTableModel;
    private GerenciaDao<Produto> produtoDao;

    public TelaGerenciamentoProdutos() {
        produtoDao = new GerenciaDao<>("produtos.dat");
        produtoTableModel = new ProdutoTableModel();

        setTitle("Gerenciamento de Produtos");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        tabelaProdutos = new JTable(produtoTableModel);
        JScrollPane scrollPane = new JScrollPane(tabelaProdutos);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelButtons = new JPanel();
        JButton btnAdicionar = new JButton("Adicionar Produto");
        JButton btnEditar = new JButton("Editar Produto");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnVoltar = new JButton("Voltar");

        panelButtons.add(btnAdicionar);
        panelButtons.add(btnEditar);
        panelButtons.add(btnRemover);
        panelButtons.add(btnVoltar);

        add(panelButtons, BorderLayout.SOUTH);


        carregarProdutos();


        btnAdicionar.addActionListener(e -> {
            new TelaCadastroProduto(produtoTableModel, null, produtoDao).setVisible(true);
        });

        btnEditar.addActionListener(e -> {
            int selectedRow = tabelaProdutos.getSelectedRow();
            if (selectedRow != -1) {
                Produto produto = produtoTableModel.getProdutoAt(selectedRow);
                new TelaCadastroProduto(produtoTableModel, produto, produtoDao).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para editar.");
            }
        });

        btnRemover.addActionListener(e -> {
            int selectedRow = tabelaProdutos.getSelectedRow();
            if (selectedRow != -1) {
                Produto produto = produtoTableModel.getProdutoAt(selectedRow);
                produtoTableModel.removerProduto(selectedRow);
                produtoDao.deletar(produto);
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um produto para remover.");
            }
        });

        btnVoltar.addActionListener(e -> dispose()); // Fecha a tela
    }

    private void carregarProdutos() {
        Set<Produto> produtos = produtoDao.getAll();
        for (Produto p : produtos) {
            produtoTableModel.adicionarProduto(p);
        }
    }
}
