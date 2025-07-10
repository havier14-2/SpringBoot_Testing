package com.perfulandiafull.perfulandiafull.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perfulandiafull.perfulandiafull.entities.Producto;
@Repository
public interface ProductoRepository extends CrudRepository<Producto,Long> {

}
