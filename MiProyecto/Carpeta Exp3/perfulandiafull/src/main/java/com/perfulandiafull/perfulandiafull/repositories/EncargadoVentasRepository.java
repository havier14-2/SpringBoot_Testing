package com.perfulandiafull.perfulandiafull.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perfulandiafull.perfulandiafull.entities.EncargadoVentas;

@Repository
public interface EncargadoVentasRepository extends CrudRepository<EncargadoVentas, Long>{

}
