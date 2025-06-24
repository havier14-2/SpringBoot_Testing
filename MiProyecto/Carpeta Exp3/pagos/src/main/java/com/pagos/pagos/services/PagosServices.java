package com.pagos.pagos.services;

import java.util.List;
import java.util.Optional;

import com.pagos.pagos.entities.Pagos;

public interface PagosServices {
    Optional<Pagos> delete(Pagos pago);
List<Pagos> findByAll();
Optional<Pagos> findById(Long id);
Pagos save(Pagos pago);


}
