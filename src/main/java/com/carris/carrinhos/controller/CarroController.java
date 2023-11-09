package com.carris.carrinhos.controller;

import com.carris.carrinhos.entity.AluguelEntity;
import com.carris.carrinhos.entity.CarroEntity;
import com.carris.carrinhos.pojo.CarroPojo;
import com.carris.carrinhos.repository.AluguelRepository;
import com.carris.carrinhos.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {
    @Autowired
    private CarroRepository carroRepository;
    @Autowired
    private AluguelRepository aluguelRepository;

    @GetMapping
    public List<CarroEntity> getAllCarros() {
        return carroRepository.findAll();
    }

    @GetMapping("/{id}")
    public CarroEntity getCarro(@PathVariable Long id) {
        return carroRepository.findById(id).orElse(null);
    }

    @PostMapping
    public CarroEntity createCarro(@RequestBody CarroPojo carro) {
        CarroEntity carroEntity = new CarroEntity();
        carroEntity.setModelo(carro.getModelo());
        carroEntity.setMarca(carro.getMarca());
        carroEntity.setPlaca(carro.getPlaca());
        return carroRepository.save(carroEntity);
    }

    @PutMapping("/{id}")
    public CarroEntity updateCarro(@PathVariable Long id, @RequestBody CarroPojo carro) {
        CarroEntity carroEntity = carroRepository.findById(id).orElse(null);
        if (carroEntity != null) {
            carroEntity.setModelo(carro.getModelo());
            carroEntity.setMarca(carro.getMarca());
            carroEntity.setPlaca(carro.getPlaca());
            return carroRepository.save(carroEntity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCarro(@PathVariable Long id) {
        carroRepository.deleteById(id);
    }

    @GetMapping("/alugados")
    public List<CarroEntity> getCarrosAlugados() {
        List<AluguelEntity> alugueis = aluguelRepository.findAll();
        List<CarroEntity> carrosAlugados = new ArrayList<>();
        for (AluguelEntity aluguel : alugueis) {
            carroRepository.findById(aluguel.getCarroId()).ifPresent(carrosAlugados::add);
        }
        return carrosAlugados;
    }
}