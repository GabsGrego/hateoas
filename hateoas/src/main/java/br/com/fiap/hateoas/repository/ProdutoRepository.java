package br.com.fiap.hateoas.repository;

import br.com.fiap.hateoas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}
