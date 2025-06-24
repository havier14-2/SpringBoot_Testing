package com.pedidos.pedidos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pedidos.pedidos.entities.Pedidos;
import com.pedidos.pedidos.respositories.PedidosRepository;

@Service
public class PedidosServicesImpl implements PedidosServices {
    @Autowired
private PedidosRepository pedidosRepository;

@Override
@Transactional
public Optional<Pedidos> delete(Pedidos unPedido) {
    Optional<Pedidos> pedidoOptional = pedidosRepository.findById(unPedido.getId());
    pedidoOptional.ifPresent(pedidoDb -> {
        pedidosRepository.delete(unPedido);
    });
    return pedidoOptional;
}

@Override
@Transactional(readOnly = true)
public List<Pedidos> findByAll() {
    return (List<Pedidos>) pedidosRepository.findAll(); // select * from pedidos
}

@Override
@Transactional(readOnly = true)
public Optional<Pedidos> findById(Long id) {
    return pedidosRepository.findById(id);
}

@Override
@Transactional
public Pedidos save(Pedidos unPedido) {
    return pedidosRepository.save(unPedido);
}


}
