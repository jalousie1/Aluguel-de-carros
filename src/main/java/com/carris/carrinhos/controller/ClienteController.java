package com.carris.carrinhos.controller;

import com.carris.carrinhos.entity.ClienteEntity;
import com.carris.carrinhos.pojo.ClientePojo;
import com.carris.carrinhos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @Operation(summary = "Pega a lista dos clientes")
    public ResponseEntity<List<ClienteEntity>> getAllClientes() {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Mostra o cliente por ID")
    public ResponseEntity<ClienteEntity> getCliente(@PathVariable Long id) {
        return ResponseEntity.of(clienteService.findById(id));
    }

    @PostMapping
    @Operation(summary = "Criar cliente")
    public ResponseEntity<ClienteEntity> createCliente(@RequestBody ClientePojo cliente) {
        return ResponseEntity.ok(clienteService.save(cliente));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar cliente por ID")
    public ResponseEntity<ClienteEntity> updateCliente(@PathVariable Long id, @RequestBody ClientePojo cliente) {
        return ResponseEntity.of(clienteService.update(id, cliente));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o cliente")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        clienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
