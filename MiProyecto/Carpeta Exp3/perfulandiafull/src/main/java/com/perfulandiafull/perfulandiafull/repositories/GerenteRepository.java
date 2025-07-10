package com.perfulandiafull.perfulandiafull.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perfulandiafull.perfulandiafull.entities.Gerente;
@Repository
public interface GerenteRepository extends CrudRepository<Gerente, Long>{

}
