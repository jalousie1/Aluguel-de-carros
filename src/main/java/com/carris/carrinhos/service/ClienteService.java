package com.carris.carrinhos.service;

import com.carris.carrinhos.entity.ClienteEntity;
import com.carris.carrinhos.pojo.ClientePojo;
import com.carris.carrinhos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteEntity> findAll() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteEntity> findById(Long id) {
        return clienteRepository.findById(id);
    }

    public ClienteEntity save(ClientePojo cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setCpf(cliente.getCpf());
        return clienteRepository.save(clienteEntity);
    }

    public Optional<ClienteEntity> update(Long id, ClientePojo cliente) {
        Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
        if (clienteEntity.isPresent()) {
            clienteEntity.get().setNome(cliente.getNome());
            clienteEntity.get().setCpf(cliente.getCpf());
            return Optional.of(clienteRepository.save(clienteEntity.get()));
        }
        return Optional.empty();
    }

    public void deleteById(Long id) {
        clienteRepository.deleteById(id);
    }
}
