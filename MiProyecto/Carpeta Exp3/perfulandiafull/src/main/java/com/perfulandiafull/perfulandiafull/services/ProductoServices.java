package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Producto;

public interface ProductoServices {
    Optional<Producto> delete(Producto producto);
    List<Producto> findByAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);

    

}
