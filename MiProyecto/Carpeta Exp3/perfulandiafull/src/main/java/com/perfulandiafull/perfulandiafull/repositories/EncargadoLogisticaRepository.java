package com.perfulandiafull.perfulandiafull.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perfulandiafull.perfulandiafull.entities.EncargadoLogistica;

@Repository
public interface EncargadoLogisticaRepository extends CrudRepository<EncargadoLogistica, Long>  {

}
