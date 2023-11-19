package com.carris.carrinhos.controller;

import com.carris.carrinhos.entity.AluguelEntity;
import com.carris.carrinhos.pojo.AluguelPojo;
import com.carris.carrinhos.service.AluguelService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelService aluguelService;

    @GetMapping
    @Operation(summary = "Mostra todos os aluguéis")
    public List<AluguelEntity> getAllAlugueis() {
        return aluguelService.getAllAlugueis();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtem aluguel por id")
    public ResponseEntity<AluguelEntity> getAluguel(@PathVariable Long id) {
        AluguelEntity aluguelEntity = aluguelService.getAluguel(id);
        if (aluguelEntity != null) {
            return ResponseEntity.ok(aluguelEntity);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Cria novo aluguel")
    public ResponseEntity<AluguelEntity> createAluguel(@Valid @RequestBody AluguelPojo aluguelPojo) {
        AluguelEntity aluguelEntity = aluguelService.createAluguel(aluguelPojo);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluguelEntity);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza aluguel")
    public ResponseEntity<AluguelEntity> updateAluguel(@PathVariable Long id, @Valid @RequestBody AluguelPojo aluguelPojo) {
        AluguelEntity aluguelEntity = aluguelService.updateAluguel(id, aluguelPojo);
        if (aluguelEntity != null) {
            return ResponseEntity.ok(aluguelEntity);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta aluguel")
    public ResponseEntity<Void> deleteAluguel(@PathVariable Long id) {
        aluguelService.deleteAluguel(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/realizarPagamento")
    @Operation(summary = "Marca o aluguel como pago")
    public ResponseEntity<AluguelEntity> realizarPagamento(@PathVariable Long id) {
        AluguelEntity aluguelEntity = aluguelService.realizarPagamento(id);
        if (aluguelEntity != null) {
            return ResponseEntity.ok(aluguelEntity);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/carrosAlugados")
    public List<AluguelEntity> getCarrosAlugados() {
        return aluguelService.getCarrosAlugados();
    }

    @GetMapping("/carrosAlugados/{clienteId}")
    public List<AluguelEntity> getCarrosAlugadosPorCliente(@PathVariable Long clienteId) {
        return aluguelService.getCarrosAlugadosPorCliente(clienteId);
    }
}
