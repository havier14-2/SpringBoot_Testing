package com.javier.springboot.ejemplito.perfulandia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javier.springboot.ejemplito.perfulandia.entities.EncargadoLogistica;
@Repository
public interface EncargadoLogisticaRepository extends CrudRepository<EncargadoLogistica, Long> {

}
