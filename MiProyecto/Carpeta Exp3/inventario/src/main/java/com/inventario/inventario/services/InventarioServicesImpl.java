package com.inventario.inventario.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventario.inventario.entities.Inventario;
import com.inventario.inventario.repositories.InventarioRepositories;


@Service

public class InventarioServicesImpl implements InventarioServices {
    @Autowired
private InventarioRepositories inventarioRepository;

@Override
@Transactional
public Optional<Inventario> delete(Inventario unInventario) {
    Optional<Inventario> inventarioOptional = inventarioRepository.findById(unInventario.getId());
    inventarioOptional.ifPresent(inventarioDb -> { 
        inventarioRepository.delete(unInventario);
    });
    return inventarioOptional;
}

@Override
@Transactional(readOnly = true)
public List<Inventario> findByAll() {
    return (List<Inventario>) inventarioRepository.findAll(); // select * from inventario
}

@Override
@Transactional(readOnly = true)
public Optional<Inventario> findById(Long id) {
    return inventarioRepository.findById(id);
}

@Override
@Transactional
public Inventario save(Inventario unInventario) {
    return inventarioRepository.save(unInventario);

}
}
