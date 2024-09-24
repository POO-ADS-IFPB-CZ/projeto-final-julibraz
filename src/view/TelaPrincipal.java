package view;

import javax.swing.*;
import java.awt.*;

public class TelaPrincipal extends JFrame {
    public TelaPrincipal() {
        setTitle("Sistema de Gerenciamento");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton btnGerenciarProdutos = new JButton("Gerenciar Produtos");
        JButton btnGerenciarFornecedores = new JButton("Gerenciar Fornecedores");
        JButton btnGerenciarUsuarios = new JButton("Gerenciar Usuários");

        setLayout(new GridLayout(3, 1));

        add(btnGerenciarProdutos);
        add(btnGerenciarFornecedores);
        add(btnGerenciarUsuarios);

        btnGerenciarProdutos.addActionListener(e -> {
            new TelaGerenciamentoProdutos().setVisible(true);
        });

        btnGerenciarFornecedores.addActionListener(e -> {
            new TelaGerenciamentoFornecedores().setVisible(true);
        });

        btnGerenciarUsuarios.addActionListener(e -> {
            new TelaGerenciamentoUsuarios().setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
