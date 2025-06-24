package com.javier.springboot.ejemplito.perfulandia.services;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.javier.springboot.ejemplito.perfulandia.entities.Cliente;
import com.javier.springboot.ejemplito.perfulandia.repositories.ClienteRepository;

@Service
public class ClienteServicesImpl implements ClienteServices{
    @Autowired
    private ClienteRepository clienterepository;

    @Override
    @Transactional
    public Optional<Cliente> delete(Cliente cliente) {
        Optional<Cliente> clienteOptional = clienterepository.findById(cliente.getId());
        clienteOptional.ifPresent(clienteDb -> {
            clienterepository.delete(clienteDb);
        });
        return clienteOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByAll() {
        return (List<Cliente>) clienterepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id) {
        return clienterepository.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente) {
        return clienterepository.save(cliente);
    }



}
