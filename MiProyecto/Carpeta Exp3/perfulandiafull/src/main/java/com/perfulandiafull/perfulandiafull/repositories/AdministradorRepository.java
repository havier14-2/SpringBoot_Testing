package com.perfulandiafull.perfulandiafull.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perfulandiafull.perfulandiafull.entities.Administrador;
@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long>{

}
