package com.perfulandiafull.perfulandiafull.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.perfulandiafull.perfulandiafull.entities.Pagos;
@Repository
public interface PagosRepository extends CrudRepository<Pagos,Long>{

}
