package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import com.perfulandiafull.perfulandiafull.entities.Pedidos;

public interface PedidosServices {
    Optional<Pedidos> delete(Pedidos pedidos);
    List<Pedidos> findByAll();
    Optional<Pedidos> findById(Long id);
    Pedidos save(Pedidos pedidos);



}
