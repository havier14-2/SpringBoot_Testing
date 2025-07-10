package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Gerente;

public interface GerenteServices {
    Optional<Gerente> delete(Gerente gerente);
    List<Gerente> findByAll();
    Optional<Gerente> findById(Long id);
    Gerente save(Gerente gerente);


}
