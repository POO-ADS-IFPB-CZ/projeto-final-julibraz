package dao;

import model.Produto;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class ProdutoDao {

    private File arquivo;

    public ProdutoDao() {
        arquivo = new File("produtos.ser");

        // Verifica se o arquivo já existe
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile(); //Cria um novo arquivo
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    // Recupera todos os produtos
    public Set<Produto> getProdutos() {
        if (arquivo.length() > 0) {
            // O arquivo tem dados, tenta ler o conjunto de produtos
            try (FileInputStream inputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

                // Desserializa o conjunto de produtos
                return (Set<Produto>) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo");
            } catch (ClassNotFoundException e) {
                System.out.println("Falha ao desserializar o arquivo");
            }
        }
        // Se o arquivo estiver vazio ou houver erro, retorna um conjunto vazio
        return new HashSet<>();
    }

    // Salvar um novo produto
    public boolean salvar(Produto produto) {
        Set<Produto> produtos = getProdutos();
        if (produtos.add(produto)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                // Serializa o conjunto de produtos no arquivo
                objectOutputStream.writeObject(produtos);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    // Deletar um produto
    public boolean deletar(Produto produto) {
        Set<Produto> produtos = getProdutos();
        if (produtos.remove(produto)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                // Serializa o conjunto de produtos atualizado no arquivo
                objectOutputStream.writeObject(produtos);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    // Buscar um produto pelo código
    public Produto buscarPorCodigo(long codigo) {
        Set<Produto> produtos = getProdutos();
        for (Produto produto : produtos) {
            if (produto.getCodigo() == codigo) {
                return produto;
            }
        }
        return null;
    }

    // Atualizar um produto
    public boolean atualizar(Produto produto) {
        return deletar(produto) && salvar(produto);
    }
}