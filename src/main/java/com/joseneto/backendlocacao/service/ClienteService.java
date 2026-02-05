package com.joseneto.backendlocacao.service;

import com.joseneto.backendlocacao.dto.ClienteDTO;
import com.joseneto.backendlocacao.entity.Cliente;
import com.joseneto.backendlocacao.mapper.ClienteMapper;
import com.joseneto.backendlocacao.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public ClienteDTO salvar(ClienteDTO dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        Cliente salvo = clienteRepository.save(cliente);
        return ClienteMapper.toDTO(salvo);
    }

    public List<ClienteDTO> listarTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(ClienteMapper::toDTO)
                .toList();
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClienteMapper.toDTO(cliente);
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    public ClienteDTO atualizar(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());

        return ClienteMapper.toDTO(clienteRepository.save(cliente));
    }
}

