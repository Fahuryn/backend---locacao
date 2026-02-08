package com.joseneto.backendlocacao.dto;

import java.time.LocalDate;

import java.time.LocalDate;

public class DisponibilidadeDTO {

    private Long tipoLocacaoId;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private boolean disponivel;

    public Long getTipoLocacaoId() {
        return tipoLocacaoId;
    }

    public void setTipoLocacaoId(Long tipoLocacaoId) {
        this.tipoLocacaoId = tipoLocacaoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }
}
