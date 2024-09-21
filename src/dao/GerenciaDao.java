package dao;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class GerenciaDao<T extends Serializable> {

    private File arquivo;

    public GerenciaDao(String fileName) {
        arquivo = new File(fileName);

        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e) {
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    public Set<T> getAll() {
        if (arquivo.length() > 0) {
            try (FileInputStream inputStream = new FileInputStream(arquivo);
                 ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {

                return (Set<T>) objectInputStream.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo");
            } catch (ClassNotFoundException e) {
                System.out.println("Falha ao desserializar o arquivo");
            }
        }
        return new HashSet<>();
    }

    public boolean salvar(T objeto) {
        Set<T> objetos = getAll();
        if (objetos.add(objeto)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                objectOutputStream.writeObject(objetos);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public boolean deletar(T objeto) {
        Set<T> objetos = getAll();
        if (objetos.remove(objeto)) {
            try (FileOutputStream outputStream = new FileOutputStream(arquivo);
                 ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {

                objectOutputStream.writeObject(objetos);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }
        }
        return false;
    }

    public boolean atualizar(T objeto) {
        return deletar(objeto) && salvar(objeto);
    }
}

