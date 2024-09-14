package dao;

import model.Usuario;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class UsuarioDao {

    private File arquivo;

    public UsuarioDao() {
        arquivo = new File("usuarios.ser");

        // Verifica se o arquivo já existe
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile(); // Cria um novo arquivo
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    // Recupera todos os usuários
    public Set<Usuario> getUsuarios() {
        if (arquivo.length() > 0) {
            // O arquivo tem dados, tenta ler o conjunto de usuários
            try (FileInputStream inputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

                // Desserializa o conjunto de usuários
                return (Set<Usuario>) objectInputStream.readObject();
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

    // Salvar um novo usuário
    public boolean salvar(Usuario usuario) {
        Set<Usuario> usuarios = getUsuarios();
        if (usuarios.add(usuario)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                // Serializa o conjunto de usuários no arquivo
                objectOutputStream.writeObject(usuarios);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    // Deletar um usuário
    public boolean deletar(Usuario usuario) {
        Set<Usuario> usuarios = getUsuarios();
        if (usuarios.remove(usuario)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                // Serializa o conjunto de usuários atualizado no arquivo
                objectOutputStream.writeObject(usuarios);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    // Buscar um usuário pelo CNPJ
    public Usuario buscarPorCnpj(String cnpj) {
        Set<Usuario> usuarios = getUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getCnpj().equals(cnpj)) {
                return usuario;
            }
        }
        return null;
    }

    // Atualizar um usuário
    public boolean atualizar(Usuario usuario) {
        return deletar(usuario) && salvar(usuario);
    }
}