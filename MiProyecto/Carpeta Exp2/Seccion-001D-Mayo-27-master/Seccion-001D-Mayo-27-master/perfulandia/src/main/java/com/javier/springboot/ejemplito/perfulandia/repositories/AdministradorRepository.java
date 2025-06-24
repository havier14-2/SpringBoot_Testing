package com.javier.springboot.ejemplito.perfulandia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javier.springboot.ejemplito.perfulandia.entities.Administrador;
@Repository
public interface AdministradorRepository extends CrudRepository<Administrador, Long> {
    


}
