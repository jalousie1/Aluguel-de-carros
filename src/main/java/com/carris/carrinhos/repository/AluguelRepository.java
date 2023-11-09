package com.carris.carrinhos.repository;

import com.carris.carrinhos.entity.AluguelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AluguelRepository extends JpaRepository<AluguelEntity, Long> {
    List<AluguelEntity> findAllByDataDevolucaoIsNull();
    List<AluguelEntity> findAllByClienteIdAndDataDevolucaoIsNull(Long clienteId);
}
