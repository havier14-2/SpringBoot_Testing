package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandiafull.perfulandiafull.entities.Pedidos;
import com.perfulandiafull.perfulandiafull.repositories.PedidosRepository;
@Service
public class PedidosServicesImpl  implements PedidosServices {
     
    @Autowired
    private PedidosRepository Pedidosrepository;


    @Override
    @Transactional
    public Optional<Pedidos> delete(Pedidos unPedidos) {
        Optional<Pedidos> PedidosOptional = Pedidosrepository.findById(unPedidos.getId());
        PedidosOptional.ifPresent(PedidosDb ->{ 
            Pedidosrepository.delete(unPedidos);
         });
        return PedidosOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findByAll() {
        return (List<Pedidos>) Pedidosrepository.findAll();   //funcionalmente select * from tabla
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedidos> findById(Long id) {
        return Pedidosrepository.findById(id);
    }

    @Override
    @Transactional
    public Pedidos save(Pedidos unPedidos) {
        return Pedidosrepository.save(unPedidos);
    }

}
