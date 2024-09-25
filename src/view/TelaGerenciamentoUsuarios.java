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
        modeloTabela.addColumn("CPF");

        tabelaUsuarios = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabelaUsuarios);

        JPanel painelEntrada = new JPanel();
        painelEntrada.setLayout(new GridLayout(5, 2));
        JTextField campoNome = new JTextField();
        JTextField campoEmail = new JTextField();
        JTextField campoCategoria = new JTextField();
        JTextField campoCPF = new JTextField();

        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(campoNome);
        painelEntrada.add(new JLabel("Email:"));
        painelEntrada.add(campoEmail);
        painelEntrada.add(new JLabel("Categoria:"));
        painelEntrada.add(campoCategoria);
        painelEntrada.add(new JLabel("CPF:"));
        painelEntrada.add(campoCPF);

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
            adicionarUsuario(campoNome, campoEmail, campoCategoria, campoCPF);
        });

        btnEditar.addActionListener(e -> {
            editarUsuario(campoNome, campoEmail, campoCategoria, campoCPF);
        });

        btnRemover.addActionListener(e -> {
            removerUsuario();
        });

        btnVoltar.addActionListener(e -> dispose()); // Fecha a tela

        add(scrollPane, BorderLayout.CENTER);
        add(painelEntrada, BorderLayout.NORTH);
        add(painelBotoes, BorderLayout.SOUTH);

        carregarUsuarios();

        // Adiciona um listener de seleção para preencher os campos de texto ao selecionar um usuário
        tabelaUsuarios.getSelectionModel().addListSelectionListener(e -> {
            int linhaSelecionada = tabelaUsuarios.getSelectedRow();
            if (linhaSelecionada >= 0) {
                // Preenche os campos de texto com os dados do usuário selecionado
                campoNome.setText((String) modeloTabela.getValueAt(linhaSelecionada, 1));
                campoEmail.setText((String) modeloTabela.getValueAt(linhaSelecionada, 2));
                campoCategoria.setText((String) modeloTabela.getValueAt(linhaSelecionada, 3));
                campoCPF.setText((String) modeloTabela.getValueAt(linhaSelecionada, 4));
            }
        });
    }

    private void carregarUsuarios() {
        modeloTabela.setRowCount(0); // Limpa a tabela antes de carregar os usuários
        Set<Usuario> usuarios = usuarioDao.getAll();
        for (Usuario usuario : usuarios) {
            Object[] rowData = new Object[]{usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCategoria(), usuario.getCpf()};
            modeloTabela.addRow(rowData);
        }
    }

    private void adicionarUsuario(JTextField campoNome, JTextField campoEmail, JTextField campoCategoria, JTextField campoCPF) {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String categoria = campoCategoria.getText();
        String cpf = campoCPF.getText();
        try {
            Usuario usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setEmail(email);
            usuario.setCategoria(categoria);
            usuario.setCpf(cpf);

            usuarioDao.salvar(usuario);
            carregarUsuarios();
            limparCampos(campoNome, campoEmail, campoCategoria, campoCPF);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editarUsuario(JTextField campoNome, JTextField campoEmail, JTextField campoCategoria, JTextField campoCPF) {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow(); // Verifica se uma linha foi selecionada
        if (linhaSelecionada >= 0) {
            // Obter o ID do usuário selecionado na tabela
            long id = (long) modeloTabela.getValueAt(linhaSelecionada, 0);

            // Buscar o usuário pelo ID
            Usuario usuario = buscarUsuarioPorId(id);

            if (usuario != null) {
                // Atualiza os dados do usuário com os valores dos campos de texto
                usuario.setNome(campoNome.getText());
                usuario.setEmail(campoEmail.getText());
                usuario.setCategoria(campoCategoria.getText());
                usuario.setCpf(campoCPF.getText());

                // Tenta atualizar o usuário no arquivo de persistência
                boolean atualizado = usuarioDao.atualizar(usuario);

                if (atualizado) {
                    // Atualizar a tabela com os novos valores
                    carregarUsuarios();

                    // Limpar os campos de entrada
                    limparCampos(campoNome, campoEmail, campoCategoria, campoCPF);

                    // Exibir mensagem de sucesso
                    JOptionPane.showMessageDialog(this, "Usuário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao atualizar o usuário.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para editar.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removerUsuario() {
        int linhaSelecionada = tabelaUsuarios.getSelectedRow();
        if (linhaSelecionada >= 0) {
            long id = (long) modeloTabela.getValueAt(linhaSelecionada, 0);
            Usuario usuario = buscarUsuarioPorId(id);

            if (usuario != null) {
                usuarioDao.deletar(usuario);
                carregarUsuarios();
            } else {
                JOptionPane.showMessageDialog(this, "Usuário não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um usuário para remover.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Usuario buscarUsuarioPorId(long id) {
        Set<Usuario> usuarios = usuarioDao.getAll();
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    private void limparCampos(JTextField campoNome, JTextField campoEmail, JTextField campoCategoria, JTextField campoCPF) {
        campoNome.setText("");
        campoEmail.setText("");
        campoCategoria.setText("");
        campoCPF.setText("");
    }
}
