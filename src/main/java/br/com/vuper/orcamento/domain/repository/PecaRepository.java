package br.com.vuper.orcamento.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.vuper.orcamento.domain.model.Peca;

@Repository
public interface PecaRepository extends JpaRepository<Peca, Long> {
}