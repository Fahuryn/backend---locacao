package com.joseneto.backendlocacao.service;

import com.joseneto.backendlocacao.dto.TipoLocacaoDTO;
import com.joseneto.backendlocacao.entity.TipoLocacao;
import com.joseneto.backendlocacao.mapper.TipoLocacaoMapper;
import com.joseneto.backendlocacao.repository.ReservaRepository;
import com.joseneto.backendlocacao.repository.TipoLocacaoRepository;
import jakarta.persistence.Entity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoLocacaoService {

    private final TipoLocacaoRepository repository;
    private final TipoLocacaoMapper mapper;
    private final ReservaRepository reservaRepository;

    public TipoLocacaoService(TipoLocacaoRepository repository,
                              TipoLocacaoMapper mapper, ReservaRepository reservaRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.reservaRepository = reservaRepository;
    }

    public  TipoLocacaoDTO criar(TipoLocacaoDTO dto) {
        TipoLocacao entity = mapper.toEntity(dto);
        System.out.println("VALOR DIÁRIA ENTITY: " + entity.getValorDiaria());
        TipoLocacao salvo = repository.save(entity);
        return mapper.toDTO(salvo);
    }

    public List<TipoLocacaoDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    public TipoLocacaoDTO buscarPorId(Long id) {
        TipoLocacao entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de locação não encontrado"));

        return mapper.toDTO(entity);
    }

    public TipoLocacaoDTO atualizar(Long id, TipoLocacaoDTO dto) {
        TipoLocacao entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de locação não encontrado"));

        entity.setNome(dto.getNome());
        entity.setDescricao(dto.getDescricao());
        entity.setAtivo(dto.getAtivo());

        TipoLocacao atualizado = repository.save(entity);
        return mapper.toDTO(atualizado);
    }

    public void deletar(Long id) {
        TipoLocacao entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de locação não encontrado"));

        boolean possuiReservas = reservaRepository.existsByTipoLocacaoId(id);

        if (possuiReservas) {
            throw new RuntimeException("Não é possivel excluir o tipo de locação pois existem reservas vinculadas");
        }

        entity.setAtivo(false);
        repository.save(entity);
    }
}
