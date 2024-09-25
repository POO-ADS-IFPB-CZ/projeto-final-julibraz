package model;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Base implements Serializable {
    private long id;
    private String nome;
    private String categoria;
    private String cnpj;
    private String cpf; // Adicionando o atributo CPF
    private String email;
    private String senhaHash;

    public Base() {}

    // Regex que irá validar formato de email
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public String getEmail() {
        return email;
    }

    // Valida se o email contém um formato aceitável
    public boolean validarEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }

    public void setEmail(String email) {
        // Irá validar se o email está em um formato aceitável antes de ser salvo
        if (validarEmail(email)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email inválido");
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        // Cobre o formato padrão de CNPJ: XX.XXX.XXX/XXXX-XX
        String CNPJ_REGEX = "\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}";
        if (Pattern.compile(CNPJ_REGEX).matcher(cnpj).matches()) {
            this.cnpj = cnpj;
        } else {
            throw new IllegalArgumentException("CNPJ inválido");
        }
    }

    public String getCpf() {
        return cpf; // Método getter para CPF
    }

    public void setCpf(String cpf) {
        // Cobre o formato padrão de CPF: XXX.XXX.XXX-XX
        String CPF_REGEX = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}";
        if (Pattern.compile(CPF_REGEX).matcher(cpf).matches()) {
            this.cpf = cpf;
        } else {
            throw new IllegalArgumentException("CPF inválido");
        }
    }

    public boolean verificarSenha(String senha) {
        return gerarHashSenha(senha).equals(this.senhaHash);
    }

    public void setSenha(String senha) {
        this.senhaHash = gerarHashSenha(senha);
    }

    // Método para gerar o hash da senha usando SHA-256
    private String gerarHashSenha(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(senha.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return id == base.id && Objects.equals(cnpj, base.cnpj) && Objects.equals(cpf, base.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj, cpf);
    }
}