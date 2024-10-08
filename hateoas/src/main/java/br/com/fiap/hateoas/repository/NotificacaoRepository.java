package br.com.fiap.hateoas.repository;

import br.com.fiap.hateoas.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
