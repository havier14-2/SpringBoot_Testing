package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Administrador;

public interface AdministradorServices {
Optional<Administrador> delete(Administrador administrador);
    List<Administrador> findByAll();
    Optional<Administrador> findById(Long id);
    Administrador save(Administrador administrador);

}

