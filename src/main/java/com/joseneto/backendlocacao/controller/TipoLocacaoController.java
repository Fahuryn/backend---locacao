package com.joseneto.backendlocacao.controller;

import com.joseneto.backendlocacao.dto.TipoLocacaoDTO;
import com.joseneto.backendlocacao.service.TipoLocacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos-locacao")
public class TipoLocacaoController {

    private final TipoLocacaoService service;

    public TipoLocacaoController(TipoLocacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TipoLocacaoDTO> criar(
            @Valid @RequestBody TipoLocacaoDTO dto) {
        TipoLocacaoDTO criado = service.criar(dto);
        return ResponseEntity.ok(criado);
    }

    @GetMapping
    public  ResponseEntity<List<TipoLocacaoDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoLocacaoDTO> buscarPorId(
            @PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoLocacaoDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody TipoLocacaoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
