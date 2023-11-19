package com.carris.carrinhos.controller;

import com.carris.carrinhos.entity.CarroEntity;
import com.carris.carrinhos.pojo.CarroPojo;
import com.carris.carrinhos.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @GetMapping
    @Operation(summary = "Pega a lista dos carros")
    public ResponseEntity<List<CarroEntity>> getAllCarros() {
        return ResponseEntity.ok(carroService.getAllCarros());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Lista dos carros por id")
    public ResponseEntity<CarroEntity> getCarro(@PathVariable Long id) {
        return ResponseEntity.of(carroService.getCarro(id));
    }

    @PostMapping
    @Operation(summary = "Cria o carro")
    public ResponseEntity<CarroEntity> createCarro(@RequestBody CarroPojo carro) {
        return ResponseEntity.ok(carroService.createCarro(carro));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza o carro")
    public ResponseEntity<CarroEntity> updateCarro(@PathVariable Long id, @RequestBody CarroPojo carro) {
        return ResponseEntity.of(carroService.updateCarro(id, carro));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o carro")
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) {
        carroService.deleteCarro(id);
        return ResponseEntity.noContent().build();
    }
}