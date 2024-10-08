package br.com.fiap.hateoas.controller;

import br.com.fiap.hateoas.entity.Produto;
import br.com.fiap.hateoas.service.ProdutoNaoEncontradoException;
import br.com.fiap.hateoas.service.ProdutoService;
import jakarta.persistence.Entity;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProdutoController {

    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<EntityModel<Produto>> criarProduto(@RequestBody Produto produto) throws ProdutoNaoEncontradoException {
        Produto produtoCriado = produtoService.criarProduto(produto);
        return ResponseEntity.ok().body(EntityModel.of(produtoCriado,
                linkTo(methodOn(ProdutoController.class)
                        .buscarProdutoPorId(produto.getId())).withSelfRel()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Produto>> buscarProdutoPorId(@PathVariable Integer id) throws ProdutoNaoEncontradoException {
        Produto produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(EntityModel.of(produto,
                linkTo(methodOn(ProdutoController.class)
                        .buscarProdutoPorId(produto.getId())).withSelfRel()));
    }

    @GetMapping()
    public ResponseEntity<List<Produto>> listarTodosProduto(@PathVariable Integer id) throws ProdutoNaoEncontradoException {
        Produto produto = produtoService.buscarProdutoPorId(id);
        return ResponseEntity.ok().body(produtoService.listarTodosProdutos());
    }
}
