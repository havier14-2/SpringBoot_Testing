package com.inventario.inventario.services;

import java.util.List;
import java.util.Optional;

import com.inventario.inventario.entities.Inventario;


public interface InventarioServices {

    Optional<Inventario> delete(Inventario inventario);
List<Inventario> findByAll();
Optional<Inventario> findById(Long id);
Inventario save(Inventario inventario);

}
