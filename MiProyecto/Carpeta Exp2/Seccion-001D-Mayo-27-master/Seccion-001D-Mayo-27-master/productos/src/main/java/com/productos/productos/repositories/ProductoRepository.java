package com.productos.productos.repositories;

import org.springframework.data.repository.CrudRepository;

import com.productos.productos.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto,Long> {

}
