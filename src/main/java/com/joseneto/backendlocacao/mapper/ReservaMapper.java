package com.joseneto.backendlocacao.mapper;

import com.joseneto.backendlocacao.dto.ReservaDTO;
import com.joseneto.backendlocacao.entity.Reserva;
import org.springframework.stereotype.Component;

@Component
public class ReservaMapper {

    public Reserva toEntity(ReservaDTO dto) {
        Reserva entity = new Reserva();
        entity.setId(dto.getId());
        entity.setDataInicio(dto.getDataInicio());
        entity.setDataFim(dto.getDataFim());
        return entity;
    }

    public ReservaDTO toDTO(Reserva entity) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(entity.getId());
        dto.setDataInicio(entity.getDataInicio());
        dto.setDataFim(entity.getDataFim());

        if (entity.getCliente() != null) {
            dto.setClienteId(entity.getCliente().getId());
        }

        if (entity.getTipoLocacao() != null) {
            dto.setTipoLocacaoId(entity.getTipoLocacao().getId());
        }

        return dto;
    }
}
