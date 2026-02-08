package com.joseneto.backendlocacao.repository;

import com.joseneto.backendlocacao.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("""
        SELECT r FROM Reserva r
        WHERE r.tipoLocacao.id = :tipoLocacaoId
        AND r.dataInicio <= :dataFim
        AND r.dataFim >= :dataInicio
    """)
    List<Reserva> buscarReservasSobrepostas(
            @Param("tipoLocacaoId") Long tipoLocacaoId,
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim
    );

    boolean existsByTipoLocacaoIdAndDataInicioLessThanAndDataFimGreaterThan(
            Long tipoLocacaoId,
            LocalDate dataInicio,
            LocalDate dataFim
    );

    boolean existsByClienteId(Long clienteId);

    boolean existsByTipoLocacaoId(Long tipoLocacaoId);
}
