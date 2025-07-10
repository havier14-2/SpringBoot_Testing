package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Pagos;

public interface PagosServices {
     Optional<Pagos> delete(Pagos pagos);
    List<Pagos> findByAll();
    Optional<Pagos> findById(Long id);
    Pagos save(Pagos pagos);



}
