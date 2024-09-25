package view;

import model.Usuario;
import dao.GerenciaDao;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroUsuario extends JFrame {
    private JTextField campoNome;
    private JTextField campoEmail;
    private JTextField campoCategoria;
    private JTextField campoCPF;
    private GerenciaDao<Usuario> usuarioDao;

    public TelaCadastroUsuario(GerenciaDao<Usuario> usuarioDao) {
        this.usuarioDao = usuarioDao;

        setTitle("Cadastrar Usuário");
        setSize(300, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2));

        panel.add(new JLabel("Nome:"));
        campoNome = new JTextField();
        panel.add(campoNome);

        panel.add(new JLabel("Email:"));
        campoEmail = new JTextField();
        panel.add(campoEmail);

        panel.add(new JLabel("Categoria:"));
        campoCategoria = new JTextField();
        panel.add(campoCategoria);

        panel.add(new JLabel("CPF:"));
        campoCPF = new JTextField();
        panel.add(campoCPF);

        JButton btnSalvar = new JButton("Salvar");
        panel.add(btnSalvar);

        add(panel, BorderLayout.CENTER);

        btnSalvar.addActionListener(e -> salvarUsuario());
    }

    private void salvarUsuario() {
        String nome = campoNome.getText();
        String email = campoEmail.getText();
        String categoria = campoCategoria.getText();
        String cpf = campoCPF.getText();

        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setCategoria(categoria);
        usuario.setCpf(cpf);

        usuarioDao.salvar(usuario);
        dispose();
    }
}
