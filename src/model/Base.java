package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.regex.Pattern;

public class Base {
    private long id;
    private String nome;
    private String categoria;
    private String cnpj;
    private String email;
    private String senhaHash;

    public Base(){}
    //Regex que ira validar formato de email
    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    public String getEmail() {
        return email;
    }
    //valida se o email contem um formato aceitavel
    public boolean validarEmail(String email) {
        return Pattern.compile(EMAIL_REGEX).matcher(email).matches();
    }
    public void setEmail(String email) {
        //ira validar se o email esta em um formato aceitavel antes de ser salvo
        if(validarEmail(email)){
            this.email = email;
        }else {
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
        this.cnpj = cnpj;
    }

    public boolean varificarSenha(String senha) {
        return gerarHashSenha(senha).equals(this.senhaHash);
    }
    public void setSenha(String senha) {
        this.senhaHash = gerarHashSenha(senha);
    }
    //Metodo para gerar o hash da senha usando SHA-256
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
        return id == base.id && Objects.equals(cnpj, base.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnpj);
    }
}
