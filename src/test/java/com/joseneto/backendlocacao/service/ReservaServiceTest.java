package com.joseneto.backendlocacao.service;

import com.joseneto.backendlocacao.dto.ReservaDTO;
import com.joseneto.backendlocacao.entity.Reserva;
import com.joseneto.backendlocacao.repository.ReservaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    @DisplayName("Deve lançar exceção quando houver conflito de datas")
    void deveLancarErroAoReservarDataOcupada() {
        ReservaDTO dto = new ReservaDTO();
        dto.setTipoLocacaoId(1L);
        dto.setDataInicio(LocalDate.of(2026, 3, 10));
        dto.setDataFim(LocalDate.of(2026, 3, 15));

        Reserva reservaExistente = new Reserva();
        List<Reserva> conflitos = List.of(reservaExistente);

        when(reservaRepository.buscarReservasSobrepostas(any(), any(), any())).thenReturn(conflitos);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            reservaService.criar(dto);
        });

        assertEquals("Já existe uma reserva para este período", exception.getMessage());
    }
}
