package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Inventario;

public interface InventarioServices {
     Optional<Inventario> delete(Inventario inventario);
    List<Inventario> findByAll();
    Optional<Inventario> findById(Long id);
    Inventario save(Inventario inventario);



}
