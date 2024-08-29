package controller;

import model.Fornecedor;
import model.Produto;
import model.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProdutoRepository {
    private Map<Long, Fornecedor> fornecedores = new HashMap<>(); //<chave, valor>
    private Map<Long, Usuario> usuarios = new HashMap<>(); //<chave, valor>
    private List<Produto> produtos = new ArrayList<>();

    public ProdutoRepository(){
        fornecedores = new HashMap<>();
        usuarios= new HashMap<>();
        produtos =  new ArrayList<>();
    }

    // Métodos para gerenciar fornecedores
    public void adicionarFornecedor(Fornecedor fornecedor) {
        fornecedores.put(fornecedor.getId(), fornecedor);
    }

    public Fornecedor buscarFornecedorPorId(long id) {
        return fornecedores.get(id);
    }

    // Métodos para gerenciar empresas
    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario buscarUsuarioPorId(long id) {
        return usuarios.get(id);
    }

    //salva um produto ou atualiza um existente
    public void salvar(Produto produto) {
        Produto existente = buscarPorCodigo(produto.getCodigo());
        if (existente != null) {
            atualizar(produto);
        } else {
            produtos.add(produto);
            System.out.println("Produto salvo com sucesso!");
        }
    }

    //fazendo a busca de um produto pelo seu código
    public Produto buscarPorCodigo(long codigo) {
        return produtos.stream()
                .filter(produto -> produto.getCodigo() == codigo) //filtra por código
                .findFirst() //localiza na lista
                .orElse(null); //retorna null se não encontrar
    }

    //atualiza um produto existente
    public void atualizar(Produto produto) {
        int index = produtos.indexOf(buscarPorCodigo(produto.getCodigo()));
        if (index != -1) {
            produtos.set(index, produto);
        }
    }

    //remove um produto pelo seu código, remove apenas a quantidade especificada
    public void removerQuantidade(long codigo, int quantidade) {
        Produto existente = buscarPorCodigo(codigo);
        if (existente != null) {
            int quantidadeExistente = existente.getQuantidade();
            if(quantidadeExistente >= quantidade) {
                existente.setQuantidade(quantidadeExistente - quantidade);
            }else {
                System.out.println("Quantidade insuficiente");
            }
        }
    }

    //remove um produto pelo seu código, tudo que estiver no estoque
    public void removerProduto(long codigo) {
        produtos.removeIf(produto -> produto.getCodigo() == codigo);
    }

    //imprime todos os produtos no estoque
    public List<Produto> listarProdutos() {
        return produtos;
    }


}
