package com.javier.springboot.ejemplito.perfulandia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javier.springboot.ejemplito.perfulandia.entities.Cliente;
@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long>{
 
}
