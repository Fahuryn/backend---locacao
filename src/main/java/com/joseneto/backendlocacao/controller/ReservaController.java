package com.joseneto.backendlocacao.controller;

import com.joseneto.backendlocacao.dto.DisponibilidadeDTO;
import com.joseneto.backendlocacao.dto.ReservaDTO;
import com.joseneto.backendlocacao.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @PostMapping
    public ResponseEntity<ReservaDTO> criar(@RequestBody @Valid ReservaDTO dto) {
        ReservaDTO reservaSalva = reservaService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSalva);
    }

    @GetMapping
    public ResponseEntity<List<ReservaDTO>> listar() {
        return ResponseEntity.ok(reservaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }

    @GetMapping("/disponibilidade")
    public ResponseEntity<DisponibilidadeDTO> verificarDisponibilidade(
            @RequestParam Long tipoLocacaoId,
            @RequestParam String dataInicio,
            @RequestParam String dataFim
    ) {
        DisponibilidadeDTO dto = reservaService.verificarDisponibilidade(
                tipoLocacaoId,
                LocalDate.parse(dataInicio),
                LocalDate.parse(dataFim)
        );

        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaDTO> atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ReservaDTO dto) {

        return ResponseEntity.ok(reservaService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        reservaService.excluir(id);
        return  ResponseEntity.noContent().build();
    }
}
