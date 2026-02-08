package com.joseneto.backendlocacao.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class ReservaDTO {

    private Long id;

    @NotNull(message = "Cliente é obrigatório")
    private Long clienteId;

    @NotNull(message = "Tipo de locação é obrigatório")
    private Long tipoLocacaoId;

    @NotNull(message = "Data início é obrigatória")
    private LocalDate dataInicio;

    @NotNull(message = "Data fim é obrigatória")
    private LocalDate dataFim;

    public ReservaDTO() {
    }

    public Long getId() {
        return id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getTipoLocacaoId() {
        return tipoLocacaoId;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setTipoLocacaoId(Long tipoLocacaoId) {
        this.tipoLocacaoId = tipoLocacaoId;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }
}

