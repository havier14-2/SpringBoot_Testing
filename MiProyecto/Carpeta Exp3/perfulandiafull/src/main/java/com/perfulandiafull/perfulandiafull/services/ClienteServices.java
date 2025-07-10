package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Cliente;

public interface ClienteServices {
    Optional<Cliente> delete(Cliente cliente);
    List<Cliente> findByAll();
    Optional<Cliente> findById(Long id);
    Cliente save(Cliente cliente);


}
