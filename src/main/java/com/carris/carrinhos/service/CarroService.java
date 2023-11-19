package com.carris.carrinhos.service;

import com.carris.carrinhos.entity.CarroEntity;
import com.carris.carrinhos.pojo.CarroPojo;
import com.carris.carrinhos.repository.CarroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    public List<CarroEntity> getAllCarros() {
        return carroRepository.findAll();
    }

    public Optional<CarroEntity> getCarro(Long id) {
        return carroRepository.findById(id);
    }

    public CarroEntity createCarro(@Valid CarroPojo carro) {
        CarroEntity carroEntity = new CarroEntity();
        carroEntity.setModelo(carro.getModelo());
        carroEntity.setMarca(carro.getMarca());
        carroEntity.setPlaca(carro.getPlaca());
        return carroRepository.save(carroEntity);
    }

    public Optional<CarroEntity> updateCarro(Long id, @Valid CarroPojo carro) {
        Optional<CarroEntity> carroEntity = carroRepository.findById(id);
        if (carroEntity.isPresent()) {
            carroEntity.get().setModelo(carro.getModelo());
            carroEntity.get().setMarca(carro.getMarca());
            carroEntity.get().setPlaca(carro.getPlaca());
            return Optional.of(carroRepository.save(carroEntity.get()));
        }
        return Optional.empty();
    }

    public void deleteCarro(Long id) {
        carroRepository.deleteById(id);
    }
}
