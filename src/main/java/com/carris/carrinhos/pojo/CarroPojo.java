package com.carris.carrinhos.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CarroPojo {

    @NotNull
    private String modelo;

    @NotNull
    private String marca;

    @NotNull
    private String placa;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
}
