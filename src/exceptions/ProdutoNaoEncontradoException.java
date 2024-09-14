package exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(long codigo) {
        super("Produto com o código " + codigo + " não encontrado");
    }
}

