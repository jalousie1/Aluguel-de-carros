package com.carris.carrinhos.controller;

import com.carris.carrinhos.entity.ClienteEntity;
import com.carris.carrinhos.pojo.ClientePojo;
import com.carris.carrinhos.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public List<ClienteEntity> getAllClientes() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClienteEntity getCliente(@PathVariable Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @PostMapping
    public ClienteEntity createCliente(@RequestBody ClientePojo cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setNome(cliente.getNome());
        clienteEntity.setCpf(cliente.getCpf());
        return clienteRepository.save(clienteEntity);
    }

    @PutMapping("/{id}")
    public ClienteEntity updateCliente(@PathVariable Long id, @RequestBody ClientePojo cliente) {
        ClienteEntity clienteEntity = clienteRepository.findById(id).orElse(null);
        if (clienteEntity != null) {
            clienteEntity.setNome(cliente.getNome());
            clienteEntity.setCpf(cliente.getCpf());
            return clienteRepository.save(clienteEntity);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteCliente(@PathVariable Long id) {
        clienteRepository.deleteById(id);
    }
}
