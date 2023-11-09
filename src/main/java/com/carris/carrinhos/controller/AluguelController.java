package com.carris.carrinhos.controller;

import com.carris.carrinhos.entity.AluguelEntity;
import com.carris.carrinhos.pojo.AluguelPojo;
import com.carris.carrinhos.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;

    @GetMapping
    public List<AluguelEntity> getAllAlugueis() {
        return aluguelRepository.findAll();
    }

    @GetMapping("/{id}")
    public AluguelEntity getAluguel(@PathVariable Long id) {
        return aluguelRepository.findById(id).orElse(null);
    }

    @PostMapping
    public AluguelEntity createAluguel(@RequestBody AluguelPojo aluguel) {
        AluguelEntity aluguelEntity = new AluguelEntity();
        aluguelEntity.setClienteId(aluguel.getClienteId());
        aluguelEntity.setCarroId(aluguel.getCarroId());
        aluguelEntity.setDataAluguel(aluguel.getDataAluguel());
        aluguelEntity.setDataDevolucao(aluguel.getDataDevolucao());
        aluguelEntity.setValorPago(aluguel.getValorPago());
        return aluguelRepository.save(aluguelEntity);
    }

    @PutMapping("/{id}")
    public AluguelEntity updateAluguel(@PathVariable Long id, @RequestBody AluguelPojo aluguel) {
        AluguelEntity aluguelEntity = aluguelRepository.findById(id).orElse(null);
        if (aluguelEntity != null) {
            aluguelEntity.setClienteId(aluguel.getClienteId());
            aluguelEntity.setCarroId(aluguel.getCarroId());
            aluguelEntity.setDataAluguel(aluguel.getDataAluguel());
            aluguelEntity.setDataDevolucao(aluguel.getDataDevolucao());
            aluguelEntity.setValorPago(aluguel.getValorPago());
            return aluguelRepository.save(aluguelEntity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAluguel(@PathVariable Long id) {
        aluguelRepository.deleteById(id);
    }

    @PostMapping("/{id}/realizarPagamento")
    public AluguelEntity realizarPagamento(@PathVariable Long id) {
        AluguelEntity aluguelEntity = aluguelRepository.findById(id).orElse(null);
        if (aluguelEntity != null) {
            aluguelEntity.setPagamentoRealizado(true);
            return aluguelRepository.save(aluguelEntity);
        }
        return null;
    }

    @GetMapping("/carrosAlugados")
    public List<AluguelEntity> getCarrosAlugados() {
        return aluguelRepository.findAllByDataDevolucaoIsNull();
    }

    @GetMapping("/carrosAlugados/{clienteId}")
    public List<AluguelEntity> getCarrosAlugadosPorCliente(@PathVariable Long clienteId) {
        return aluguelRepository.findAllByClienteIdAndDataDevolucaoIsNull(clienteId);
    }
}
