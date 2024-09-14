package dao;

import model.Fornecedor;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FornecedorDao {

    private File arquivo;

    public FornecedorDao() {
        arquivo = new File("fornecedores.ser");

        // Verifica se o arquivo já existe
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile(); // Cria um novo arquivo
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    // Recupera todos os fornecedores
    public Set<Fornecedor> getFornecedores() {
        if (arquivo.length() > 0) {
            // O arquivo tem dados, tenta ler o conjunto de fornecedores
            try (FileInputStream inputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

                // Desserializa o conjunto de fornecedores
                return (Set<Fornecedor>) objectInputStream.readObject();
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

    // Salvar um novo fornecedor
    public boolean salvar(Fornecedor fornecedor) {
        Set<Fornecedor> fornecedores = getFornecedores();
        if (fornecedores.add(fornecedor)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                // Serializa o conjunto de fornecedores no arquivo
                objectOutputStream.writeObject(fornecedores);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    // Deletar um fornecedor
    public boolean deletar(Fornecedor fornecedor) {
        Set<Fornecedor> fornecedores = getFornecedores();
        if (fornecedores.remove(fornecedor)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                // Serializa o conjunto de fornecedores atualizado no arquivo
                objectOutputStream.writeObject(fornecedores);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    // Buscar um fornecedor pelo CNPJ
    public Fornecedor buscarPorCnpj(String cnpj) {
        Set<Fornecedor> fornecedores = getFornecedores();
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getCnpj().equals(cnpj)) {
                return fornecedor;
            }
        }
        return null;
    }

    // Atualizar um fornecedor
    public boolean atualizar(Fornecedor fornecedor) {
        return deletar(fornecedor) && salvar(fornecedor);
    }
}