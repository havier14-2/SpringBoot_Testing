package com.javier.springboot.ejemplito.perfulandia.services;

import java.util.List;
import java.util.Optional;

import com.javier.springboot.ejemplito.perfulandia.entities.Administrador;


public interface AdministradorServices {
    Optional<Administrador> delete(Administrador administrador);
    List<Administrador> findByAll();
    Optional<Administrador> findById(Long id);
    Administrador save(Administrador administrador);

}
