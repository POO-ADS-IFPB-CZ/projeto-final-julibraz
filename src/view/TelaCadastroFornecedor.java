package view;

import model.Fornecedor;
import dao.GerenciaDao;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroFornecedor extends JFrame {
    private JTextField txtNome;
    private JTextField txtCNPJ;
    private JTextField txtEmail;
    private JTextField txtId;
    private FornecedorTableModel fornecedorTableModel;
    private Fornecedor fornecedor;
    private GerenciaDao<Fornecedor> fornecedorDao;

    public TelaCadastroFornecedor(FornecedorTableModel model, Fornecedor fornecedorExistente, GerenciaDao<Fornecedor> fornecedorDao) {
        this.fornecedorTableModel = model;
        this.fornecedor = fornecedorExistente;
        this.fornecedorDao = fornecedorDao;

        setTitle(fornecedorExistente == null ? "Cadastrar Fornecedor" : "Editar Fornecedor");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Nome:"));
        txtNome = new JTextField();
        panel.add(txtNome);

        panel.add(new JLabel("CNPJ:"));
        txtCNPJ = new JTextField();
        panel.add(txtCNPJ);

        panel.add(new JLabel("Email:"));
        txtEmail = new JTextField();
        panel.add(txtEmail);

        panel.add(new JLabel("Id:"));
        txtId = new JTextField();
        panel.add(txtId);

        JButton btnSalvar = new JButton("Salvar");
        panel.add(btnSalvar);

        if (fornecedorExistente != null) {
            txtNome.setText(fornecedorExistente.getNome());
            txtCNPJ.setText(fornecedorExistente.getCnpj());
            txtEmail.setText(fornecedorExistente.getEmail());
            txtId.setText(String.valueOf(fornecedorExistente.getId()));

        }

        add(panel, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvarFornecedor());
    }

    private void salvarFornecedor() {
        String nome = txtNome.getText();
        String cnpj = txtCNPJ.getText();
        String email = txtEmail.getText();
        long id = Long.parseLong(txtId.getText());
        try {
            if (fornecedor == null) {
                fornecedor = new Fornecedor();
                fornecedor.setNome(nome);
                fornecedor.setCnpj(cnpj);
                fornecedor.setEmail(email);
                fornecedor.setId(id);
                fornecedorTableModel.adicionarFornecedor(fornecedor);
                fornecedorDao.salvar(fornecedor);
            } else {
                fornecedor.setNome(nome);
                fornecedor.setCnpj(cnpj);
                fornecedorTableModel.fireTableDataChanged();
                fornecedorDao.atualizar(fornecedor);
            }

            dispose();
        }catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}