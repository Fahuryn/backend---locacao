package com.joseneto.backendlocacao.service;

import com.joseneto.backendlocacao.dto.DisponibilidadeDTO;
import com.joseneto.backendlocacao.dto.ReservaDTO;
import com.joseneto.backendlocacao.entity.Cliente;
import com.joseneto.backendlocacao.entity.Reserva;
import com.joseneto.backendlocacao.entity.TipoLocacao;
import com.joseneto.backendlocacao.mapper.ReservaMapper;
import com.joseneto.backendlocacao.repository.ClienteRepository;
import com.joseneto.backendlocacao.repository.ReservaRepository;
import com.joseneto.backendlocacao.repository.TipoLocacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final TipoLocacaoRepository tipoLocacaoRepository;
    private final ReservaMapper reservaMapper;

    public ReservaService(
            ReservaRepository reservaRepository,
            ClienteRepository clienteRepository,
            TipoLocacaoRepository tipoLocacaoRepository,
            ReservaMapper reservaMapper
    ) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.tipoLocacaoRepository = tipoLocacaoRepository;
        this.reservaMapper = reservaMapper;
    }

    public ReservaDTO criar(ReservaDTO dto) {

        validarDatas(dto.getDataInicio(), dto.getDataFim());

        validarConflitoDeDatas(
                dto.getTipoLocacaoId(),
                dto.getDataInicio(),
                dto.getDataFim(),
                null
        );

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        TipoLocacao tipoLocacao = tipoLocacaoRepository.findById(dto.getTipoLocacaoId())
                .orElseThrow(() -> new RuntimeException("Tipo de locação não encontrado"));

        Reserva reserva = reservaMapper.toEntity(dto);
        reserva.setCliente(cliente);
        reserva.setTipoLocacao(tipoLocacao);

        Reserva salva = reservaRepository.save(reserva);
        return reservaMapper.toDTO(salva);
    }

    public List<ReservaDTO> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(reservaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ReservaDTO buscarPorId(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        return reservaMapper.toDTO(reserva);
    }

    public ReservaDTO atualizar(Long id, ReservaDTO dto) {

        validarDatas(dto.getDataInicio(), dto.getDataFim());

        validarConflitoDeDatas(
                dto.getTipoLocacaoId(),
                dto.getDataInicio(),
                dto.getDataFim(),
                id
        );


        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        TipoLocacao tipoLocacao = tipoLocacaoRepository.findById(dto.getTipoLocacaoId())
                .orElseThrow(() -> new RuntimeException("Tipo de locação não encontrado"));

        reserva.setDataInicio(dto.getDataInicio());
        reserva.setDataFim(dto.getDataFim());
        reserva.setCliente(cliente);
        reserva.setTipoLocacao(tipoLocacao);

        Reserva atualizada = reservaRepository.save(reserva);
        return reservaMapper.toDTO(atualizada);
    }

    public void excluir(Long id) {
        if (!reservaRepository.existsById(id)) {
            throw new RuntimeException("Reserva não encontrada");
        }
        reservaRepository.deleteById(id);
    }

    public DisponibilidadeDTO verificarDisponibilidade(
            Long tipoLocacaoId,
            LocalDate dataInicio,
            LocalDate dataFim
    ) {
        validarDatas(dataInicio, dataFim);

        boolean existeConflito = !reservaRepository.buscarReservasSobrepostas(
                tipoLocacaoId,
                dataInicio,
                dataFim
        ).isEmpty();

        DisponibilidadeDTO dto = new DisponibilidadeDTO();
        dto.setTipoLocacaoId(tipoLocacaoId);
        dto.setDataInicio(dataInicio);
        dto.setDataFim(dataFim);
        dto.setDisponivel(!existeConflito);

        return dto;
    }

    private void validarDatas(LocalDate inicio, LocalDate fim) {
        if (inicio == null || fim == null) {
            throw new RuntimeException("Datas de início e fim são obrigatórias");
        }

        if (fim.isBefore(inicio)) {
            throw new RuntimeException("Data final não pode ser antes da data inicial");
        }
    }

    private void validarConflitoDeDatas(
            Long tipoLocacaoId,
            LocalDate dataInicio,
            LocalDate dataFim,
            Long reservaId
    ) {
        List<Reserva> conflitos = reservaRepository.buscarReservasSobrepostas(
                tipoLocacaoId,
                dataInicio,
                dataFim
        );

        if (reservaId != null) {
            conflitos = conflitos.stream()
                    .filter(r -> !r.getId().equals(reservaId))
                    .toList();
        }

        if (!conflitos.isEmpty()) {
            throw new RuntimeException("Já existe uma reserva para este período");
        }
    }
}
