package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.javier.springboot.ejemplito.perfulandia.entities.Gerente;

public interface GerenteServices {
    Optional<Gerente> delete(Gerente gerente);
    List<Gerente> findByAll();
    Optional<Gerente> findById(Long id);
    Gerente save(Gerente gerente);


}
