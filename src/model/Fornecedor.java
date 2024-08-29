package model;

public class Fornecedor extends Base{
    private String cnpj;
    private String contato;
    private String email;

    public Fornecedor() {
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        super.setName(name);
    }

    public void setCategoria(String categoria) {
        super.setCategoria(categoria);
    }
}
