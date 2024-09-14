package exceptions;

public class QuantidadeInsuficienteException extends RuntimeException{
    public QuantidadeInsuficienteException(int quantidadeAtual, int quantidadeSolicitada) {
        super("Quantidade insuficiente. Quantidade disponivel: " + quantidadeAtual + ", quantidade solicitada: " + quantidadeSolicitada);

    }
}
