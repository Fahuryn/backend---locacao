package com.joseneto.backendlocacao.repository;

import com.joseneto.backendlocacao.entity.TipoLocacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoLocacaoRepository extends JpaRepository<TipoLocacao, Long> {
}
