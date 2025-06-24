package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.javier.springboot.ejemplito.perfulandia.entities.Cliente;

public interface ClienteServices {
    Optional<Cliente> delete(Cliente cliente);
    List<Cliente> findByAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);


}
