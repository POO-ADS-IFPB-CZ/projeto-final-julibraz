package view;

import model.Produto;
import dao.GerenciaDao;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroProduto extends JFrame {
    private JTextField txtCodigo;
    private JTextField txtDescricao;
    private JTextField txtValor;
    private JTextField txtQuantidade;
    private ProdutoTableModel produtoTableModel;
    private Produto produto;
    private GerenciaDao<Produto> produtoDao;

    public TelaCadastroProduto(ProdutoTableModel model, Produto produtoExistente, GerenciaDao<Produto> produtoDao) {
        this.produtoTableModel = model;
        this.produto = produtoExistente;
        this.produtoDao = produtoDao;

        setTitle(produtoExistente == null ? "Cadastrar Produto" : "Editar Produto");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Código:"));
        txtCodigo = new JTextField();
        panel.add(txtCodigo);

        panel.add(new JLabel("Descrição:"));
        txtDescricao = new JTextField();
        panel.add(txtDescricao);

        panel.add(new JLabel("Valor:"));
        txtValor = new JTextField();
        panel.add(txtValor);

        panel.add(new JLabel("Quantidade:"));
        txtQuantidade = new JTextField();
        panel.add(txtQuantidade);

        JButton btnSalvar = new JButton("Salvar");
        panel.add(btnSalvar);

        if (produtoExistente != null) {
            txtCodigo.setText(String.valueOf(produtoExistente.getCodigo()));
            txtDescricao.setText(produtoExistente.getDescricao());
            txtValor.setText(String.valueOf(produtoExistente.getValor()));
            txtQuantidade.setText(String.valueOf(produtoExistente.getQuantidade()));
        }

        add(panel, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvarProduto());
    }

    private void salvarProduto() {
        long codigo = Long.parseLong(txtCodigo.getText());
        String descricao = txtDescricao.getText();
        double valor = Double.parseDouble(txtValor.getText());
        int quantidade = Integer.parseInt(txtQuantidade.getText());

        if (produto == null) {
            produto = new Produto();
            produto.setCodigo(codigo);
            produto.setDescricao(descricao);
            produto.setValor(valor);
            produto.setQuantidade(quantidade);
            produtoTableModel.adicionarProduto(produto);
            produtoDao.salvar(produto);
        } else {
            produto.setCodigo(codigo);
            produto.setDescricao(descricao);
            produto.setValor(valor);
            produto.setQuantidade(quantidade);
            produtoTableModel.fireTableDataChanged();
            produtoDao.atualizar(produto);
        }

        dispose();
    }
}
