package br.com.fiap.hateoas.service;

import br.com.fiap.hateoas.entity.Produto;
import br.com.fiap.hateoas.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    public Produto criarProduto(Produto produto){
        produtoRepository.save(produto);
        return produto;
    }

    public Produto buscarProdutoPorId(Integer id) throws ProdutoNaoEncontradoException {
       return buscarPorId(id);
    }

    public void deletarProdutoPorID(Integer id) throws ProdutoNaoEncontradoException {
        buscarPorId(id);
        produtoRepository.deleteById(id);
    }

    private Produto buscarPorId(Integer id) throws ProdutoNaoEncontradoException {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            throw new ProdutoNaoEncontradoException("id nao encontrado" + id);
        }
        return produtoOptional.get();
    }

    public List<Produto> listarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public Produto atualizarProduto(Integer id, Produto produto) throws ProdutoNaoEncontradoException {
        Produto produtoAntigo = buscarProdutoPorId(id);
        produtoAntigo.setId(produto.getId());
        produtoAntigo.setNome(produto.getNome());
        produtoAntigo.setPreco(produto.getPreco());
        produtoRepository.save(produtoAntigo);
        return produtoAntigo;
    }
}
