package view;

import model.Usuario;
import dao.GerenciaDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Set;

public class TelaGerenciamentoUsuarios extends JFrame {
    private JTable tabelaUsuarios;
    private DefaultTableModel modeloTabela;
    private GerenciaDao<Usuario> usuarioDao;

    public TelaGerenciamentoUsuarios() {
        usuarioDao = new GerenciaDao<>("usuarios.dat");
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Email");
        modeloTabela.addColumn("Categoria");
        modeloTabela.addColumn("CNPJ");

        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new GridLayout(5, 2));
        JTextField campoNome = new JTextField();
        JTextField campoEmail = new JTextField();
        JTextField campoCategoria = new JTextField();
        JTextField campoCNPJ = new JTextField();

        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(campoNome);
        painelEntrada.add(new JLabel("Email:"));
        painelEntrada.add(campoEmail);
        painelEntrada.add(new JLabel("Categoria:"));
        painelEntrada.add(campoCategoria);
        painelEntrada.add(new JLabel("CNPJ:"));
        painelEntrada.add(campoCNPJ);

        JButton btnAdicionar = new JButton("Adicionar");
        JButton btnEditar = new JButton("Editar");
        JButton btnRemover = new JButton("Remover");
        JButton btnVoltar = new JButton("Voltar");

        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(btnAdicionar);
        painelBotoes.add(btnEditar);
        painelBotoes.add(btnRemover);
        painelBotoes.add(btnVoltar);

        btnAdicionar.addActionListener(e -> {
            adicionarUsuario(campoNome, campoEmail, campoCategoria, campoCNPJ);
        });

        btnEditar.addActionListener(e -> {
            editarUsuario(campoNome, campoEmail, campoCategoria, campoCNPJ);
        });

        btnRemover.addActionListener(e -> {
            removerUsuario();
        });

        btnVoltar.addActionListener(e -> dispose()); // Fecha a tela

        add(scrollPane, BorderLayout.CENTER);
        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarUsuarios();
    }

    private void carregarUsuarios() {
        Set<Usuario> usuarios = usuarioDao.getAll();
        for (Usuario usuario : usuarios) {
            Object[] rowData = new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCategoria(), usuario.getCnpj()};
            modeloTabela.addRow(rowData);
        }
    }

    private void adicionarUsuario(JTextField campoNome, JTextField campoEmail, JTextField campoCategoria, JTextField campoCNPJ) {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String categoria = campoCategoria.getText();
        String cnpj = campoCNPJ.getText();
    try {
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCategoria(categoria);
        usuario.setCnpj(cnpj);

        usuarioDao.salvar(usuario);
        atualizarTabela();
        limparCampos(campoNome, campoEmail, campoCategoria, campoCNPJ);
    }catch (IllegalArgumentException e) {
        JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
    }
    }

    private void editarUsuario(JTextField campoNome, JTextField campoEmail, JTextField campoCategoria, JTextField campoCNPJ) {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            long id = (long) modeloTabela.getValueAt(linhaSelecionada, 0);
            Usuario usuario = new Usuario(); // Obter o usuário pelo ID
            usuario.setNome(campoNome.getText());
            usuario.setEmail(campoEmail.getText());
            usuario.setCategoria(campoCategoria.getText());
            usuario.setCnpj(campoCNPJ.getText());

            usuarioDao.atualizar(usuario);
            atualizarTabela();
            limparCampos(campoNome, campoEmail, campoCategoria, campoCNPJ);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerUsuario() {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            long id = (long) modeloTabela.getValueAt(linhaSelecionada, 0);
            Usuario usuario = new Usuario();
            usuarioDao.deletar(usuario);
            atualizarTabela();
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void atualizarTabela() {
        modeloTabela.setRowCount(0);
        carregarUsuarios();
    }

    private void limparCampos(JTextField campoNome, JTextField campoEmail, JTextField campoCategoria, JTextField campoCNPJ) {
        campoNome.setText("");
        campoEmail.setText("");
        campoCategoria.setText("");
        campoCNPJ.setText("");
    }
}
