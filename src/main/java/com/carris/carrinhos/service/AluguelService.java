package com.carris.carrinhos.service;

import com.carris.carrinhos.entity.AluguelEntity;
import com.carris.carrinhos.exception.AluguelException;
import com.carris.carrinhos.pojo.AluguelPojo;
import com.carris.carrinhos.repository.AluguelRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public List<AluguelEntity> getAllAlugueis() {
        return aluguelRepository.findAll();
    }

    public AluguelEntity getAluguel(@NotNull Long id) {
        return aluguelRepository.findById(id).orElse(null);
    }

    public AluguelEntity createAluguel(@Valid AluguelPojo aluguelPojo) {
        if (!aluguelPojo.isValid()) {
            throw new AluguelException("Aluguel inválido");
        }

        AluguelEntity aluguelEntity = new AluguelEntity();
        aluguelEntity.setClienteId(aluguelPojo.getClienteId());
        aluguelEntity.setCarroId(aluguelPojo.getCarroId());
        aluguelEntity.setDataAluguel(aluguelPojo.getDataAluguel());
        aluguelEntity.setDataDevolucao(aluguelPojo.getDataDevolucao());
        aluguelEntity.setValorPago(aluguelPojo.getValorPago());
        return aluguelRepository.save(aluguelEntity);
    }

    public AluguelEntity updateAluguel(@NotNull Long id, @Valid AluguelPojo aluguelPojo) {
        AluguelEntity aluguelEntity = aluguelRepository.findById(id).orElse(null);
        if (aluguelEntity != null) {
            if (!aluguelPojo.isValid()) {
                throw new AluguelException("Aluguel inválido");
            }

            aluguelEntity.setClienteId(aluguelPojo.getClienteId());
            aluguelEntity.setCarroId(aluguelPojo.getCarroId());
            aluguelEntity.setDataAluguel(aluguelPojo.getDataAluguel());
            aluguelEntity.setDataDevolucao(aluguelPojo.getDataDevolucao());
            aluguelEntity.setValorPago(aluguelPojo.getValorPago());
            return aluguelRepository.save(aluguelEntity);
        }
        return null;
    }

    public void deleteAluguel(@NotNull Long id) {
        aluguelRepository.deleteById(id);
    }

    public AluguelEntity realizarPagamento(@NotNull Long id) {
        AluguelEntity aluguelEntity = aluguelRepository.findById(id).orElse(null);
        if (aluguelEntity != null) {
            aluguelEntity.setPagamentoRealizado(true);
            return aluguelRepository.save(aluguelEntity);
        }
        return null;
    }

    public List<AluguelEntity> getCarrosAlugados() {
        return aluguelRepository.findAllByDataDevolucaoIsNull();
    }

    public List<AluguelEntity> getCarrosAlugadosPorCliente(@NotNull Long clienteId) {
        return aluguelRepository.findAllByClienteIdAndDataDevolucaoIsNull(clienteId);
    }
}
