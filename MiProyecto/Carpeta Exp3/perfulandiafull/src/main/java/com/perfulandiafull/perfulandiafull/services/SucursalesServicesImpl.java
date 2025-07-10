package com.perfulandiafull.perfulandiafull.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfulandiafull.perfulandiafull.entities.Sucursales;
import com.perfulandiafull.perfulandiafull.repositories.SucursalesRepository;
@Service
public class SucursalesServicesImpl  implements SucursalesServices {
     @Autowired
    private SucursalesRepository sucursalRepository;

@Override
@Transactional  
public Optional<Sucursales> delete(Sucursales unaSucursal) {
    Optional<Sucursales> sucursalOptional = sucursalRepository.findById(unaSucursal.getId());
    sucursalOptional.ifPresent(sucursalDb -> { 
        sucursalRepository.delete(unaSucursal);
    });
    return sucursalOptional;
}

@Override
@Transactional(readOnly = true)
public List<Sucursales> findByAll() {
    return (List<Sucursales>) sucursalRepository.findAll(); 
}

@Override
@Transactional(readOnly = true)
public Optional<Sucursales> findById(Long id) {
    return sucursalRepository.findById(id);
}

@Override
@Transactional
public Sucursales save(Sucursales unaSucursal) {
    return sucursalRepository.save(unaSucursal);
}

}
