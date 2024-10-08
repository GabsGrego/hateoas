package br.com.fiap.hateoas.service;

public class ProdutoNaoEncontradoException extends Throwable {
    public ProdutoNaoEncontradoException(String mensagem) {
        super(mensagem);
    }
}
