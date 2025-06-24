package com.productos.productos.services;

import java.util.List;
import java.util.Optional;

import com.productos.productos.entities.Producto;

public interface ProductoServices {
       Optional<Producto> delete(Producto producto);
    List<Producto> findByAll();
    Optional<Producto> findById(Long id);
    Producto save(Producto producto);



}
