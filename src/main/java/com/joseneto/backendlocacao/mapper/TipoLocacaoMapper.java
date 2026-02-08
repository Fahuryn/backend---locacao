package com.joseneto.backendlocacao.mapper;

import com.joseneto.backendlocacao.dto.TipoLocacaoDTO;
import com.joseneto.backendlocacao.entity.TipoLocacao;
import org.springframework.stereotype.Component;

@Component
public class TipoLocacaoMapper {

    public TipoLocacao toEntity(TipoLocacaoDTO dto) {
        TipoLocacao entity = new TipoLocacao();
        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setAtivo(dto.getAtivo() != null ? dto.getAtivo() : true);
        entity.setValorDiaria(dto.getValorDiaria());
        return entity;
    }

    public TipoLocacaoDTO toDTO(TipoLocacao entity) {
        TipoLocacaoDTO dto = new TipoLocacaoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setDescricao(entity.getDescricao());
        dto.setAtivo(entity.getAtivo());
        dto.setValorDiaria(entity.getValorDiaria());
        return dto;
    }
}
