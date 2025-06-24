package com.pedidos.pedidos.services;

import java.util.List;
import java.util.Optional;

import com.pedidos.pedidos.entities.Pedidos;

public interface PedidosServices {
    Optional<Pedidos> delete(Pedidos pedido);
List<Pedidos> findByAll();
Optional<Pedidos> findById(Long id);
Pedidos save(Pedidos pedido);

}
