package com.carris.carrinhos.pojo;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class AluguelPojo {

    private Long id;
    @NotNull
    private Long clienteId;
    @NotNull
    private Long carroId;
    private Date dataAluguel;
    private Date dataDevolucao;
    private Double valorPago;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCarroId() {
        return carroId;
    }

    public void setCarroId(Long carroId) {
        this.carroId = carroId;
    }

    public Date getDataAluguel() {
        return dataAluguel;
    }

    public void setDataAluguel(Date dataAluguel) {
        this.dataAluguel = dataAluguel;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public boolean isValid() {
        if (clienteId == null || clienteId < 1) {
            return false;
        }

        // Valida o carroId
        if (carroId == null || carroId < 1) {
            return false;
        }

        // Valida a data de aluguel
        if (dataAluguel == null) {
            return false;
        }

        // Valida a data de devolução
        if (dataDevolucao != null && dataDevolucao.before(dataAluguel)) {
            return false;
        }

        // Valida o valor pago
        if (valorPago != null && valorPago < 0) {
            return false;
        }

        return true;
    }
}
